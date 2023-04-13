/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hemoser.bdspr.view;

import com.hemoser.bdspr.service.PerfilService;
import com.hemoser.bdspr.util.Util;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author max
 */
@ManagedBean
public class CargaImagenPerfilView implements Serializable {

    @ManagedProperty("#{perfilService}")
    private PerfilService perfilService;
    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload() {
        if (file != null) {
            FacesMessage message = new FacesMessage("Correcto", file.getFileName() + " archivo cargado.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void handleFileUpload(FileUploadEvent event) {

        if (perfilService.setImagen(event.getFile().getContent())) {
            Util.addInfoMessage("Correcto", event.getFile().getFileName() + " archivo cargado.");
        }else{
            Util.addErrMessage("No se logró cargar la imagen.");
        }
        
    }

    public void setPerfilService(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

}
