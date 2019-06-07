package com.mex.SpringBootProject.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cidade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Idcidade")
    private Long idCidade;
    @Column
    private String descricao;
    @Column
    private boolean ativo;
    @Column(name = "UF")
    private String estado;

    public Cidade() {
    }

    public Cidade(String nome) {
        this.descricao = nome;
    }

    public Cidade(Long idCidade, String descricao, boolean ativo, String estado) {
        this.idCidade = idCidade;
        this.descricao = descricao;
        this.ativo = ativo;
        this.estado = estado;
    }

    /**
     * @return the idCidade
     */
    public Long getIdCidade() {
        return idCidade;
    }

    /**
     * @param idCidade the idCidade to set
     */
    public void setIdCidade(Long idCidade) {
        this.idCidade = idCidade;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the ativo
     */
    public boolean isAtivo() {
        return ativo;
    }

    /**
     * @param ativo the ativo to set
     */
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
