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
public class AreaServicioM implements Serializable {

    private Integer fncveareaservicio;
    private String fcareaservicionombre;

    public Integer getFncveareaservicio() {
        return fncveareaservicio;
    }

    public void setFncveareaservicio(Integer fncveareaservicio) {
        this.fncveareaservicio = fncveareaservicio;
    }

    public String getFcareaservicionombre() {
        return fcareaservicionombre;
    }

    public void setFcareaservicionombre(String fcareaservicionombre) {
        this.fcareaservicionombre = fcareaservicionombre;
    }

}
