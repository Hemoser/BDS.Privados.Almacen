/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hemoser.bdspr.service;

import com.hemoser.bdspr.model.PerfilM;
import com.hemoser.bdspr.model.PerfilMenuM;
import com.hemoser.bdspr.model.UsuarioLogInM;
import com.hemoser.bdspr.util.Util;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.StreamedContent;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.DefaultStreamedContent;

/**
 *
 * @author max
 */
@ManagedBean
@SessionScoped
public class PerfilService implements Serializable {

    private UsuarioLogInM usuario;
    private PerfilM perfil;
    private byte[] imagen;
    private Boolean permiteCompletar;

    private List<PerfilMenuM> authorizedPages = new ArrayList();

    public StreamedContent getImagenPerfil() {
        return DefaultStreamedContent.builder()
                .contentType("image/png")
                .stream(() -> {
                    return new ByteArrayInputStream(imagen);
                }).build();

    }

    public PerfilM getPerfil() {
        return perfil;
    }

    public UsuarioLogInM getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioLogInM user) {
        try {
            perfil = GeneralService.selectPerfilById(user.getPerfilid());
            imagen = GeneralService.getImagenPerfil(user.getFnuserid());
            authorizedPages = GeneralService.selectPerfilMenu();
            permiteCompletar = perfil.getFbcompletartareas();
            this.usuario = user;
        } catch (Exception ex) {
            Util.addExceptionMessage(ex);
        }
    }

    public byte[] getImagen() {
        return imagen;
    }

    public boolean getPermiteCompletar() {
        return permiteCompletar;
    }

    public boolean setImagen(byte[] imagen) {
        Boolean resultado;
        resultado = GeneralService.updateImagenPerfil(usuario.getFnuserid(), imagen);
        if (resultado) {
            this.imagen = imagen;
        }
        return resultado;
    }

    public boolean menuIsActive(String menuId) {
        PerfilMenuM item = authorizedPages.stream().filter(i -> i.getMenuid().equals(menuId)).findAny().orElse(null);
        if (item == null) {
            return true;
        } else {
            return item.isActivo();
        }
    }
}
