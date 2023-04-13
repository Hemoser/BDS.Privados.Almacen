/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hemoser.bdspr.model;

import java.io.Serializable;

/**
 *
 * @author Ricardo SR
 */
public class InstitucionM implements Serializable {

    private Integer fncveinstitucion;
    private String fcinstitucion;

    public Integer getFncveinstitucion() {
        return fncveinstitucion;
    }

    public void setFncveinstitucion(Integer fncveinstitucion) {
        this.fncveinstitucion = fncveinstitucion;
    }

    public String getFcinstitucion() {
        return fcinstitucion;
    }

    public void setFcinstitucion(String fcinstitucion) {
        this.fcinstitucion = fcinstitucion;
    }

}
