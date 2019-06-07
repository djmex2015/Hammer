package com.mex.SpringBootProject.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "APPLICATION_INFO")
public class ApplicationInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String action;
    @Column
    private int atualizados;
    @Column
    private int noAtualizados;
    @Column
    private int total;
    @Column
    private Date time;
    @Column
    private long timeEjec;

    public ApplicationInfo() {
    }

    public ApplicationInfo(String action, int atualizados, int noAtualizados, int total, long timeEjec) {
        this.action = action;
        this.atualizados = atualizados;
        this.noAtualizados = noAtualizados;
        this.timeEjec = timeEjec;
        this.time = new Date();
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * @return the time
     */
    public Date getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * @return the atualizados
     */
    public int getAtualizados() {
        return atualizados;
    }

    /**
     * @param atualizados the atualizados to set
     */
    public void setAtualizados(int atualizados) {
        this.atualizados = atualizados;
    }

    /**
     * @return the noAtualizados
     */
    public int getNoAtualizados() {
        return noAtualizados;
    }

    /**
     * @param noAtualizados the noAtualizados to set
     */
    public void setNoAtualizados(int noAtualizados) {
        this.noAtualizados = noAtualizados;
    }

    /**
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * @return the timeEjec
     */
    public long getTimeEjec() {
        return timeEjec;
    }

    /**
     * @param timeEjec the timeEjec to set
     */
    public void setTimeEjec(long timeEjec) {
        this.timeEjec = timeEjec;
    }
    
    
    

}
