/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hemoser.bdspr.model;

import java.io.Serializable;

/**
 *
 * @author Sergio Morales
 */
public class PerfilMenuM implements Serializable {

    private Integer perfilmenuid;
    private Integer perfilid;
    private String menuid;
    private String nombre;
    private String tipo;
    private boolean activo;

    public PerfilMenuM() {
    }

    // <editor-fold defaultstate="collapsed" desc="Properties">
    public Integer getPerfilmenuid() {
        return perfilmenuid;
    }

    public void setPerfilmenuid(Integer perfilmenuid) {
        this.perfilmenuid = perfilmenuid;
    }

    public Integer getPerfilid() {
        return perfilid;
    }

    public void setPerfilid(Integer perfilid) {
        this.perfilid = perfilid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    // </editor-fold>

}
