package com.mex.SpringBootProject.services;

import com.mex.SpringBootProject.configuration.MessageException;
import com.mex.SpringBootProject.entities.Cidade;
import com.mex.SpringBootProject.entities.Participante;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Date;
import java.util.List;

public interface FormService {

    public List<Participante> getPartipantesByDate(Date from, Date to);

    public Cidade getCidadeByNomeAndEstado(String nome, String estado);

    public int updateInfo(Date from, Date to) throws MalformedURLException, IOException, MessageException;

    public void generateReport(String msgResult) throws IOException;

    public void logInfo(String msgResult, int atualizados, int noAtualizados, int total, long timeEjec);
}
