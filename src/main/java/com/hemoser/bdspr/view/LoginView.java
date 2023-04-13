/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hemoser.bdspr.view;

import com.hemoser.bdspr.model.UsuarioLogInM;
import com.hemoser.bdspr.service.LogInService;
import com.hemoser.bdspr.service.PerfilService;
import com.hemoser.bdspr.service.SeguridadService;
import com.hemoser.bdspr.util.Util;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author max
 */
@ManagedBean(name = "loginView")
@ViewScoped
public class LoginView implements Serializable {

    @ManagedProperty("#{perfilService}")
    private PerfilService perfilService;

    private String username;
    private String password;
    private SeguridadService SeguridadService;
    private UsuarioLogInM usuario;

    @PostConstruct
    public void init() {
        try {
            username = Util.getBase64Param("USRNM");
        } catch (Exception ex) {
            Util.addErrMessage("Error al iniciar sesión. Revise el Log.");
            Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void login() {
        try {
            usuario = LogInService.validaUsuario(username, password);

            if (usuario == null) {
                Util.addErrMessage("Usuario y/o contraseña incorrectos.");
                return;
            }

            if (!usuario.isFnactivo()) {
                Util.addErrMessage("Usuario inactivo.");
                return;
            }
            Util.setUserId(usuario.getFnuserid());
            perfilService.setUsuario(usuario);

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentUser", username);

            Util.addInfoMessage("Bienvenido", username);

            SeguridadService = new SeguridadService();
            SeguridadService.init();
            if (SeguridadService.menuIsActive("mi_dashboard")) {
                Util.redirect("/dashboard.xhtml");
            } else if (SeguridadService.menuIsActive("mi_insumos")) {
                Util.redirect("/insumos/inventario/list.xhtml");
            }
        } catch (Exception ex) {
            Util.addErrMessage("Error al iniciar sesión. Revise el Log.");
            Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void tryLogInExternal() {
        try {
            if (username == null || username.isEmpty()) {
                return;
            }
            usuario = LogInService.validateCredentials(username);
            if (!usuario.getFcusername().isEmpty()) {
                username = usuario.getFcusername();
                password = usuario.getFcpassword();
                login();
            } else {
                username = null;
                password = null;
                Util.addErrMessage("EL USUARIO NO CUENTA CON LOS PERMISOS NECESARIOS");
            }
        } catch (Exception ex) {
            Util.addExceptionMessage(ex);
            Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Properties">
    public void setPerfilService(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    public SeguridadService getSeguridadService() {
        return SeguridadService;
    }

    public void setSeguridadService(SeguridadService SeguridadService) {
        this.SeguridadService = SeguridadService;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    // </editor-fold>

}
