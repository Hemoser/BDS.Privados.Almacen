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
public class AlmacenVM implements Serializable {

    private String institucion;
    private Integer unidad;
    private String componente;
    private Date fechaExtraccion;
    private Date fechaCaducidad;
    private Integer volumen;
    private String nombre;
    private Integer donadorId;
    private Boolean pediatrica;
    private Boolean pool;
    private String gpoSang;

//<editor-fold defaultstate="collapsed" desc="Getters - Setters">
    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public Integer getUnidad() {
        return unidad;
    }

    public void setUnidad(Integer unidad) {
        this.unidad = unidad;
    }

    public String getComponente() {
        return componente;
    }

    public void setComponente(String componente) {
        this.componente = componente;
    }

    public Date getFechaExtraccion() {
        return fechaExtraccion;
    }

    public void setFechaExtraccion(Date fechaExtraccion) {
        this.fechaExtraccion = fechaExtraccion;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public Integer getVolumen() {
        return volumen;
    }

    public void setVolumen(Integer volumen) {
        this.volumen = volumen;
    }

    public Integer getDonadorId() {
        return donadorId;
    }

    public void setDonadorId(Integer donadorId) {
        this.donadorId = donadorId;
    }

    public Boolean getPediatrica() {
        return pediatrica;
    }

    public void setPediatrica(Boolean pediatrica) {
        this.pediatrica = pediatrica;
    }

    public Boolean getPool() {
        return pool;
    }

    public void setPool(Boolean pool) {
        this.pool = pool;
    }

    public String getGpoSang() {
        return gpoSang;
    }

    public void setGpoSang(String gpoSang) {
        this.gpoSang = gpoSang;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
//</editor-fold>

}
