/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hemoser.bdspr.view;

import com.hemoser.bdspr.model.UsuarioM;
import com.hemoser.bdspr.service.UsuarioService;
import com.hemoser.bdspr.util.Util;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Sergio Morales
 */
@ManagedBean(name = "CuentaView")
@ViewScoped
public class CuentaView implements Serializable {

    private UsuarioM editItem;
    private String fcpassword1 = "";
    private String fcpassword2 = "";

    @PostConstruct
    public void init() {
        try {
            editItem = UsuarioService.selectUsuarioById(Util.getUserId());
        } catch (Exception ex) {
            Logger.getLogger(CuentaView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void edit() {
        try {
            UsuarioService.updateUsuario(editItem);
            Util.addInfoMessage("Los cambios se realizaron correctamente");
            Util.redirect("cuenta.xhtml");
        } catch (Exception ex) {
            Util.addExceptionMessage(ex);
            Logger.getLogger(CuentaView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editPassword() {
        try {
            if (!fcpassword2.equals(fcpassword1)) {
                Util.addErrMessage("Los valores no coinciden.");
                return;
            }
            editItem.setFcpassword(fcpassword1);
            UsuarioService.updateUsuarioPassword(editItem);
            Util.addInfoMessage("Los cambios se realizaron correctamente");
            Util.redirect("cuenta.xhtml");
        } catch (Exception ex) {
            Util.addExceptionMessage(ex);
            Logger.getLogger(CuentaView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Properties">
    public UsuarioM getEditItem() {
        return editItem;
    }

    public void setEditItem(UsuarioM editItem) {
        this.editItem = editItem;
    }

    public String getFcpassword1() {
        return fcpassword1;
    }

    public void setFcpassword1(String fcpassword1) {
        this.fcpassword1 = fcpassword1;
    }

    public String getFcpassword2() {
        return fcpassword2;
    }

    public void setFcpassword2(String fcpassword2) {
        this.fcpassword2 = fcpassword2;
    }
    // </editor-fold>

}
