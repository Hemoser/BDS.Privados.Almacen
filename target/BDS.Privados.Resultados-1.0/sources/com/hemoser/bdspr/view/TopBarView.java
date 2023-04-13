/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hemoser.bdspr.view;

import com.hemoser.bdspr.model.ParametroM;
import com.hemoser.bdspr.service.GeneralService;
import com.hemoser.bdspr.util.Util;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

/**
 *
 * @author f_per
 */
@ManagedBean(name = "TopBarView")
@SessionScoped
public class TopBarView implements Serializable {

    @PostConstruct
    public void init() {
    }

    public void onIdle() throws IOException {

    }

    public void onActive() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                "Activity detected", "User is active"));
    }

    public void timerActividad() {
        GeneralService.updateCurrentSession();
    }

    public void logout() {
        Util.redirect("/logout.xhtml");
    }

    //<editor-fold defaultstate="collapsed" desc="Getters - Setters">
    //</editor-fold>
}
