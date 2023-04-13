/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hemoser.bdspr.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Sergio Morales
 */
public class UsuarioM implements Serializable {

    private Integer fnuserid;
    private String fcusername;
    private String fcnombre;
    private String fcpassword;
    private Boolean fbactivo;
    private String fcpaterno;
    private String fcmaterno;
    private Boolean fbsuspendido;
    private Date fdsuspendido;
    private Integer fncveinstitucion;
    private String fctelefono;
    private String fccorreo;
    private Integer perfilid;
    private Boolean fbcolaborador;
    private Integer fncveempresa;
    private Boolean fbvertareas;
    private Integer fncveperfil_areasatencion;
    private Boolean fbconfidenciales;

    public UsuarioM() {
    }

    // <editor-fold defaultstate="collapsed" desc="Properties">
    public Integer getFnuserid() {
        return fnuserid;
    }

    public void setFnuserid(Integer fnuserid) {
        this.fnuserid = fnuserid;
    }

    public Boolean getFbvertareas() {
        return fbvertareas;
    }

    public void setFbvertareas(Boolean fbvertareas) {
        this.fbvertareas = fbvertareas;
    }

    public String getFcusername() {
        return fcusername;
    }

    public void setFcusername(String fcusername) {
        this.fcusername = fcusername;
    }

    public String getFcnombre() {
        return fcnombre;
    }

    public void setFcnombre(String fcnombre) {
        this.fcnombre = fcnombre;
    }

    public String getFcpassword() {
        return fcpassword;
    }

    public void setFcpassword(String fcpassword) {
        this.fcpassword = fcpassword;
    }

    public Boolean getFbactivo() {
        return fbactivo;
    }

    public void setFbactivo(Boolean fbactivo) {
        this.fbactivo = fbactivo;
    }

    public String getFcpaterno() {
        return fcpaterno;
    }

    public void setFcpaterno(String fcpaterno) {
        this.fcpaterno = fcpaterno;
    }

    public String getFcmaterno() {
        return fcmaterno;
    }

    public void setFcmaterno(String fcmaterno) {
        this.fcmaterno = fcmaterno;
    }

    public Boolean getFbsuspendido() {
        return fbsuspendido;
    }

    public void setFbsuspendido(Boolean fbsuspendido) {
        this.fbsuspendido = fbsuspendido;
    }

    public Date getFdsuspendido() {
        return fdsuspendido;
    }

    public void setFdsuspendido(Date fdsuspendido) {
        this.fdsuspendido = fdsuspendido;
    }

    public Integer getFncveinstitucion() {
        return fncveinstitucion;
    }

    public void setFncveinstitucion(Integer fncveinstitucion) {
        this.fncveinstitucion = fncveinstitucion;
    }

    public String getFctelefono() {
        return fctelefono;
    }

    public void setFctelefono(String fctelefono) {
        this.fctelefono = fctelefono;
    }

    public String getFccorreo() {
        return fccorreo;
    }

    public void setFccorreo(String fccorreo) {
        this.fccorreo = fccorreo;
    }

    public Integer getPerfilid() {
        return perfilid;
    }

    public void setPerfilid(Integer perfilid) {
        this.perfilid = perfilid;
    }

    public Boolean getFbcolaborador() {
        return fbcolaborador;
    }

    public void setFbcolaborador(Boolean fbcolaborador) {
        this.fbcolaborador = fbcolaborador;
    }

    public Integer getFncveempresa() {
        return fncveempresa;
    }

    public void setFncveempresa(Integer fncveempresa) {
        this.fncveempresa = fncveempresa;
    }

    public Integer getFncveperfil_areasatencion() {
        return fncveperfil_areasatencion;
    }

    public void setFncveperfil_areasatencion(Integer fncveperfil_areasatencion) {
        this.fncveperfil_areasatencion = fncveperfil_areasatencion;
    }

    public Boolean getFbconfidenciales() {
        return fbconfidenciales;
    }

    public void setFbconfidenciales(Boolean fbconfidenciales) {
        this.fbconfidenciales = fbconfidenciales;
    }

    // </editor-fold>
}
