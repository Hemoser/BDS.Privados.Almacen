/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hemoser.bdspr.view;

import com.hemoser.bdspr.service.LogInService;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author max
 */
@ManagedBean(name = "logoutView")
@RequestScoped
public class LogoutView implements Serializable {

    private Date fecha;

    @PostConstruct
    public void init() {
        fecha = new Date();
        logout();
    }

    public void logout() {
        // LogInService.clearSessionActivity();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
