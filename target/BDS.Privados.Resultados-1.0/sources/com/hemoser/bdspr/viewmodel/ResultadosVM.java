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
public class ResultadosVM implements Serializable {

    private Integer resultadoId;
    private Boolean confidencial;
    private Date fechaOrden;
    private String nombre;
    private String analisis;
    private String resultado;
    private String usuarioValida;
    private String matriculaValida;
    private Date fechaValida;
    private String tipo;
    private Integer donadorPacienteId;
    private Integer serologiaId;
    private Integer status;

//<editor-fold defaultstate="collapsed" desc="Getters - Setters">
    public Integer getResultadoId() {
        return resultadoId;
    }

    public void setResultadoId(Integer resultadoId) {
        this.resultadoId = resultadoId;
    }

    public Boolean getConfidencial() {
        return confidencial;
    }

    public void setConfidencial(Boolean confidencial) {
        this.confidencial = confidencial;
    }

    public Date getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(Date fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAnalisis() {
        return analisis;
    }

    public void setAnalisis(String analisis) {
        this.analisis = analisis;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getUsuarioValida() {
        return usuarioValida;
    }

    public void setUsuarioValida(String usuarioValida) {
        this.usuarioValida = usuarioValida;
    }

    public String getMatriculaValida() {
        return matriculaValida;
    }

    public void setMatriculaValida(String matriculaValida) {
        this.matriculaValida = matriculaValida;
    }

    public Date getFechaValida() {
        return fechaValida;
    }

    public void setFechaValida(Date fechaValida) {
        this.fechaValida = fechaValida;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getDonadorPacienteId() {
        return donadorPacienteId;
    }

    public void setDonadorPacienteId(Integer donadorPacienteId) {
        this.donadorPacienteId = donadorPacienteId;
    }

    public Integer getSerologiaId() {
        return serologiaId;
    }

    public void setSerologiaId(Integer serologiaId) {
        this.serologiaId = serologiaId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
//</editor-fold>
}
