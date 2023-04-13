/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hemoser.bdspr.model;

import java.io.Serializable;

/**
 *
 * @author Sergio
 */
public class PerfilMenuHorizontalM implements Serializable {

    private Integer MenuHorizontalId;
    private int MenuId;
    private int PerfilId;
    private String Label;
    private String Descripcion;
    private boolean Habilitar;

    //<editor-fold defaultstate="collapsed" desc="Properties">
    public Integer getMenuHorizontalId() {
        return MenuHorizontalId;
    }

    public void setMenuHorizontalId(Integer MenuHorizontalId) {
        this.MenuHorizontalId = MenuHorizontalId;
    }

    public int getMenuId() {
        return MenuId;
    }

    public void setMenuId(int MenuId) {
        this.MenuId = MenuId;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String Label) {
        this.Label = Label;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public boolean isHabilitar() {
        return Habilitar;
    }

    public void setHabilitar(boolean Habilitar) {
        this.Habilitar = Habilitar;
    }

    public int getPerfilId() {
        return PerfilId;
    }

    public void setPerfilId(int PerfilId) {
        this.PerfilId = PerfilId;
    }
    //</editor-fold>

    @Override
    public String toString() {
        return "PerfilMenuHorizontalM{" + "MenuHorizontalId=" + MenuHorizontalId + ", Habilitar=" + Habilitar + '}';
    }

}
