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
public class ComponentesM implements Serializable {

    private Integer fraccionId;
    private String fraccion;

    public Integer getFraccionId() {
        return fraccionId;
    }

    public void setFraccionId(Integer fraccionId) {
        this.fraccionId = fraccionId;
    }

    public String getFraccion() {
        return fraccion;
    }

    public void setFraccion(String fraccion) {
        this.fraccion = fraccion;
    }

}
