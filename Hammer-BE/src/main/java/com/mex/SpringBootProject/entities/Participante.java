package com.mex.SpringBootProject.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
public class Participante implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Idparticipante")
    private Long idParticipante;
    @Column(name = "Datainclusao")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataInclusao;
    private char sexo;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "Datanascimento")
    private Date dataNascimento;
    @Column(length = 8)
    private String cep;
    @Column(length = 128)
    private String logradouro;
    @Column(length = 128)
    private String complemento;
    @Column(length = 128)
    private String bairro;
    @OneToOne
    @JoinColumn(name = "Idcidade")
    private Cidade idCidade;
    @Column(length = 1024)
    private String zona;
    @Column(name = "Idestudo")
    private Long idEstudo;

    public Participante() {
    }

    public Participante(Long idParticipante, Date dataInclusao, char sexo, Date dataNascimento,
            String cep, String logradouro, String complemento, String bairro, Cidade idCidade, String zona, Long idEstudo) {
        this.idParticipante = idParticipante;
        this.dataInclusao = dataInclusao;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.cep = cep;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.idCidade = idCidade;
        this.zona = zona;
        this.idEstudo = idEstudo;
    }

    /**
     * @return the idParticipante
     */
    public Long getIdParticipante() {
        return idParticipante;
    }

    /**
     * @param idParticipante the idParticipante to set
     */
    public void setIdParticipante(Long idParticipante) {
        this.idParticipante = idParticipante;
    }

    /**
     * @return the dataInclusao
     */
    public Date getDataInclusao() {
        return dataInclusao;
    }

    /**
     * @param dataInclusao the dataInclusao to set
     */
    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    /**
     * @return the sexo
     */
    public char getSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    /**
     * @return the dataNascimento
     */
    public Date getDataNascimento() {
        return dataNascimento;
    }

    /**
     * @param dataNascimento the dataNascimento to set
     */
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * @param cep the cep to set
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * @return the logradouro
     */
    public String getLogradouro() {
        return logradouro;
    }

    /**
     * @param logradouro the logradouro to set
     */
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    /**
     * @return the complemento
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * @param complemento the complemento to set
     */
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    /**
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @param bairro the bairro to set
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * @return the idCidade
     */
    public Cidade getIdCidade() {
        return idCidade;
    }

    /**
     * @param idCidade the idCidade to set
     */
    public void setIdCidade(Cidade idCidade) {
        this.idCidade = idCidade;
    }

    /**
     * @return the zona
     */
    public String getZona() {
        return zona;
    }

    /**
     * @param zona the zona to set
     */
    public void setZona(String zona) {
        this.zona = zona;
    }

    /**
     * @return the idEstudo
     */
    public Long getIdEstudo() {
        return idEstudo;
    }

    /**
     * @param idEstudo the idEstudo to set
     */
    public void setIdEstudo(Long idEstudo) {
        this.idEstudo = idEstudo;
    }

}
