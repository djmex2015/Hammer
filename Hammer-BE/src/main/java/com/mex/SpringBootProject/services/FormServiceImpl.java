package com.mex.SpringBootProject.services;

import static com.mex.SpringBootProject.configuration.Constants.BAIRRO;
import static com.mex.SpringBootProject.configuration.Constants.CEP;
import static com.mex.SpringBootProject.configuration.Constants.CHROME;
import static com.mex.SpringBootProject.configuration.Constants.ESTADO;
import static com.mex.SpringBootProject.configuration.Constants.HTTP_AGENT;
import static com.mex.SpringBootProject.configuration.Constants.HTTP_AGENT_VALUE;
import static com.mex.SpringBootProject.configuration.Constants.LOCALIDADE;
import static com.mex.SpringBootProject.configuration.Constants.LOGRADOURO;
import static com.mex.SpringBootProject.configuration.Constants.REPORT_MESSAGE;
import static com.mex.SpringBootProject.configuration.Constants.REPORT_OUTPUT;
import static com.mex.SpringBootProject.configuration.Constants.URL_CEP;
import static com.mex.SpringBootProject.configuration.Constants.URL_ENDERECO;
import com.mex.SpringBootProject.configuration.MessageException;
import com.mex.SpringBootProject.entities.ApplicationInfo;
import com.mex.SpringBootProject.entities.Cidade;
import com.mex.SpringBootProject.entities.Participante;
import com.mex.SpringBootProject.repositories.IAppInfoRepository;
import com.mex.SpringBootProject.repositories.ICidadeRepository;
import com.mex.SpringBootProject.repositories.IParticipanteRepository;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;
import java.util.Objects;
import javax.ws.rs.core.HttpHeaders;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("formService")
public class FormServiceImpl implements FormService {

    Logger log = LogManager.getLogger(FormServiceImpl.class);

    ICidadeRepository repoCidade;

    IParticipanteRepository repoPart;

    IAppInfoRepository infoRepo;

    @Autowired
    public FormServiceImpl(ICidadeRepository repoCidade, IParticipanteRepository repoPart, IAppInfoRepository infoRepo) {
        this.repoCidade = repoCidade;
        this.repoPart = repoPart;
        this.infoRepo = infoRepo;
    }

    @Override
    public List<Participante> getPartipantesByDate(Date from, Date to) {
        return repoPart.getPartipantesByDate(from, to);
    }

    @Override
    public Cidade getCidadeByNomeAndEstado(String nome, String estado) {
        return repoCidade.getCidadeByDescricaoAndEstado(nome, estado);
    }

    @Override
    @Transactional
    public int updateInfo(Date from, Date to) throws MalformedURLException, IOException, MessageException {
        int partUpdated = 0;

        int partNoUpdated = 0;
        
        long startTime = System.nanoTime();

        List<Participante> participantes = repoPart.getPartipantesByDate(from, to);

        for (Participante t : participantes) {
            System.setProperty(HTTP_AGENT, CHROME);
            if (Objects.isNull(t.getCep()) || t.getCep().isEmpty()) {
                if (Objects.nonNull(t.getIdCidade()) && Objects.nonNull(t.getIdCidade().getEstado()) && Objects.nonNull(t.getIdCidade().getDescricao()) && Objects.nonNull(t.getLogradouro())) {
                    URL urlEndereco = new URL(String.format(URL_ENDERECO, t.getIdCidade().getEstado(), t.getIdCidade().getDescricao(), t.getLogradouro()));
                    HttpURLConnection httpcon = (HttpURLConnection) urlEndereco.openConnection();
                    httpcon.addRequestProperty(HttpHeaders.USER_AGENT, HTTP_AGENT_VALUE);
                    try (InputStream input = httpcon.getInputStream()) {
                        JSONObject obj = new JSONObject(IOUtils.toString(input, StandardCharsets.UTF_8));
                        t.setCep(obj.getString(CEP));
                        if (!t.getBairro().equalsIgnoreCase(obj.get(BAIRRO).toString()) || !t.getLogradouro().equalsIgnoreCase(obj.get(LOGRADOURO).toString()) || !t.getIdCidade().getDescricao().equalsIgnoreCase(obj.get(LOCALIDADE).toString())) {
                            t.setLogradouro(obj.getString(LOGRADOURO));
                            t.setBairro(obj.getString(BAIRRO));
                            Cidade cidade = repoCidade.getCidadeByDescricaoAndEstado(obj.getString(LOCALIDADE), obj.getString(ESTADO));
                            t.setIdCidade(cidade);
                            partUpdated++;
                        } else {
                            partNoUpdated++;
                        }
                        repoPart.save(t);

                    } catch (IOException | JSONException e) {
                        log.warn(String.format("Participante %s nao actualizado (endereco inexistente)", t.getIdParticipante()));
                        partNoUpdated++;
                    }
                } else {
                    log.warn(String.format("Participante %s nao actualizado (dados de endereco y cep inexistentes.)", t.getIdParticipante()));
                    partNoUpdated++;
                }
            } else {
                URL urlCep = new URL(String.format(URL_CEP, t.getCep()));
                HttpURLConnection httpcon = (HttpURLConnection) urlCep.openConnection();
                httpcon.addRequestProperty(HttpHeaders.USER_AGENT, HTTP_AGENT_VALUE);
                try (InputStream input = httpcon.getInputStream()) {
                    JSONObject obj = new JSONObject(IOUtils.toString(input, StandardCharsets.UTF_8));
                    if (!t.getBairro().equalsIgnoreCase(obj.get(BAIRRO).toString()) || !t.getLogradouro().equalsIgnoreCase(obj.get(LOGRADOURO).toString()) || !t.getIdCidade().getDescricao().equalsIgnoreCase(obj.get(LOCALIDADE).toString())) {
                        t.setLogradouro(obj.getString(LOGRADOURO));
                        t.setBairro(obj.getString(BAIRRO));
                        Cidade cidade = repoCidade.getCidadeByDescricaoAndEstado(obj.getString(LOCALIDADE), obj.getString(ESTADO));
                        t.setIdCidade(cidade);
                        repoPart.save(t);
                        partUpdated++;
                    } else {
                        partNoUpdated++;
                    }
                } catch (IOException | JSONException e) {
                    log.warn(String.format("Participante %s nao actualizado (endereco inexistente)", t.getIdParticipante()));
                    partNoUpdated++;
                }
            }
        }
        long endTime = System.nanoTime() - startTime;
        infoRepo.save(new ApplicationInfo("UPDATE", partUpdated, partNoUpdated, participantes.size(), endTime));
        String message = String.format(REPORT_MESSAGE, partUpdated, partNoUpdated, participantes.size());
        generateReport(message);
        return partUpdated;
    }

    @Override
    public void generateReport(String msgResult) throws IOException {
        Path path = Paths.get(REPORT_OUTPUT);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(msgResult);
        }
    }

    @Override
    public void logInfo(String msgResult, int atualizados, int noAtualizados, int total, long timeEjec) {
        infoRepo.save(new ApplicationInfo(msgResult, atualizados, noAtualizados, total, timeEjec));
    }

}
