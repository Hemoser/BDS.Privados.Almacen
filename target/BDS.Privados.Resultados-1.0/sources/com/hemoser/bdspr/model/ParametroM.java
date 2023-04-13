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
public class ParametroM implements Serializable {

    private Integer fncveparametro;
    private Integer fnminutossesion;
    private Integer fnminutosbloqueo;
    private Integer fnminutosidle;
    private int milisecondsIdle;
    private Integer fnminutosrefresh;
    private Boolean fbgeolocalizacion;
    private Integer fnminutosrefresh_nuevastareas;

    public ParametroM() {
    }

    // <editor-fold defaultstate="collapsed" desc="Properties">
    public int getMilisecondsIdle() {
        return milisecondsIdle;
    }

    public void setMilisecondsIdle(int milisecondsIdle) {
        this.milisecondsIdle = milisecondsIdle;
    }

    public Integer getFnminutosrefresh() {
        return fnminutosrefresh;
    }

    public void setFnminutosrefresh(Integer fnminutosrefresh) {
        this.fnminutosrefresh = fnminutosrefresh;
    }

    public Integer getFncveparametro() {
        return fncveparametro;
    }

    public void setFncveparametro(Integer fncveparametro) {
        this.fncveparametro = fncveparametro;
    }

    public Integer getFnminutossesion() {
        return fnminutossesion;
    }

    public void setFnminutossesion(Integer fnminutossesion) {
        this.fnminutossesion = fnminutossesion;
    }

    public Integer getFnminutosbloqueo() {
        return fnminutosbloqueo;
    }

    public void setFnminutosbloqueo(Integer fnminutosbloqueo) {
        this.fnminutosbloqueo = fnminutosbloqueo;
    }

    public Integer getFnminutosidle() {
        return fnminutosidle;
    }

    public void setFnminutosidle(Integer fnminutosidle) {
        this.fnminutosidle = fnminutosidle;
    }

    public Boolean getFbgeolocalizacion() {
        return fbgeolocalizacion;
    }

    public void setFbgeolocalizacion(Boolean fbgeolocalizacion) {
        this.fbgeolocalizacion = fbgeolocalizacion;
    }

    public Integer getFnminutosrefresh_nuevastareas() {
        return fnminutosrefresh_nuevastareas;
    }

    public void setFnminutosrefresh_nuevastareas(Integer fnminutosrefresh_nuevastareas) {
        this.fnminutosrefresh_nuevastareas = fnminutosrefresh_nuevastareas;
    }
    // </editor-fold> 

}
