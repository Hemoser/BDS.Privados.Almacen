/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hemoser.bdspr.model;

import java.util.Base64;
import java.util.Date;

/**
 *
 * @author f_per
 */
public class UsuarioLogInM {

    private Integer fnuserid;
    private String fcusername;
    private String fcnombre;
    private String fcpassword;
    private Integer perfilid;
    private boolean fnactivo;
    private String fcpaterno;
    private String fcmaterno;

    public UsuarioLogInM() {

    }

    // <editor-fold defaultstate="collapsed" desc="Properties">
    public Integer getFnuserid() {
        return fnuserid;
    }

    public void setFnuserid(Integer fnuserid) {
        this.fnuserid = fnuserid;
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

    public Integer getPerfilid() {
        return perfilid;
    }

    public void setPerfilid(Integer perfilid) {
        this.perfilid = perfilid;
    }

    public boolean isFnactivo() {
        return fnactivo;
    }

    public void setFnactivo(boolean fnactivo) {
        this.fnactivo = fnactivo;
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

    // </editor-fold>
}
