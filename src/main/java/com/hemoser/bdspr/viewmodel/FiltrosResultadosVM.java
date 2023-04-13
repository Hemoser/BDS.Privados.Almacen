/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hemoser.bdspr.viewmodel;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Ricardo SR
 */
public class FiltrosResultadosVM implements Serializable {

    private Integer institucionId;
    private String nombre;
    private String paterno;
    private String materno;
    private Date fod;
    private Date foh;
    private String analisis;
    private Integer status;
    private Date frd;
    private Date frh;
    private String servicio;

    public Integer getInstitucionId() {
        return institucionId;
    }

    public void setInstitucionId(Integer institucionId) {
        this.institucionId = institucionId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public Date getFod() {
        return fod;
    }

    public void setFod(Date fod) {
        this.fod = fod;
    }

    public Date getFoh() {
        return foh;
    }

    public void setFoh(Date foh) {
        this.foh = foh;
    }

    public String getAnalisis() {
        return analisis;
    }

    public void setAnalisis(String analisis) {
        this.analisis = analisis;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getFrd() {
        return frd;
    }

    public void setFrd(Date frd) {
        this.frd = frd;
    }

    public Date getFrh() {
        return frh;
    }

    public void setFrh(Date frh) {
        this.frh = frh;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

}
