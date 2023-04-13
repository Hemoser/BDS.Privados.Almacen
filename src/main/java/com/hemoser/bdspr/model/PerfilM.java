/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hemoser.bdspr.model;

import java.io.Serializable;

/**
 *
 * @author max
 */
public class PerfilM implements Serializable {

    private Integer perfilid;
    private String perfil;
    private boolean activo;
    private Boolean fbcompletartareas;

    // <editor-fold defaultstate="collapsed" desc="Properties">
    public Integer getPerfilid() {
        return perfilid;
    }

    public void setPerfilid(Integer perfilid) {
        this.perfilid = perfilid;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Boolean getFbcompletartareas() {
        return fbcompletartareas;
    }

    public void setFbcompletartareas(Boolean fbcompletartareas) {
        this.fbcompletartareas = fbcompletartareas;
    }
    // </editor-fold>

}
