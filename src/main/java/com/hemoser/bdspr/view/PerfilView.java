/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hemoser.bdspr.view;

import com.hemoser.bdspr.model.PerfilM;
import com.hemoser.bdspr.model.PerfilMenuM;
import com.hemoser.bdspr.service.GeneralService;
import com.hemoser.bdspr.util.Util;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author max
 */
@ManagedBean(name = "PerfilView")
@ViewScoped
public class PerfilView implements Serializable {

    private PerfilM editItem;
    private List<PerfilM> rows;
    private List<PerfilMenuM> perfilMenuItems;
    private PerfilM selectedRow;
    private String accion;
    private Integer perfilid;

    @PostConstruct
    public void init() {
        try {
            accion = Util.getBase64Param("OP");
            switch (accion) {
                case "ADD":
                    editItem = new PerfilM();
                    editItem.setActivo(true);
                    break;
                case "EDIT":
                    perfilid = Util.tryParseInteger(Util.getBase64Param("ID"));
                    editItem = GeneralService.selectPerfilById(perfilid);
                    break;
                default:
                    rows = GeneralService.selectPerfiles();
                    break;
            }

            if (accion.equals("ADD") || accion.equals("EDIT")) {
                perfilMenuItems = GeneralService.selectPerfilMenuByPerfilId(perfilid);
            }
        } catch (Exception ex) {
            Logger.getLogger(PerfilView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void onRowUnselected(UnselectEvent event) {
        selectedRow = null;
    }

    public void saveAction() {
        try {
            if (accion.equals("ADD")) {
                add();
            } else {
                edit();
            }
        } catch (Exception ex) {
            Util.addExceptionMessage(ex);
            Logger.getLogger(PerfilView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void add() throws Exception {
        if (GeneralService.addPerfil(editItem)) {
            GeneralService.addPerfilMenuItems(editItem.getPerfilid(), perfilMenuItems);
            Util.addInfoMessage("Se agregó el perfil correctamente");
            redirect();
        } else {
            Util.addErrMessage("No se logró agregar el perfil");
        }
    }

    public void edit() throws Exception {
        if (GeneralService.updatePerfil(editItem)) {
            GeneralService.addPerfilMenuItems(editItem.getPerfilid(), perfilMenuItems);
            Util.addInfoMessage("Se modificaron los datos correctamente");
            redirect();
        } else {
            Util.addErrMessage("No se logró modificar el perfil");
        }
    }

    public void redirect() {
        Util.redirect("list.xhtml");
    }

    // <editor-fold defaultstate="collapsed" desc="Properties">
    public PerfilM getEditItem() {
        return editItem;
    }

    public void setEditItem(PerfilM editItem) {
        this.editItem = editItem;
    }

    public List<PerfilM> getRows() {
        return rows;
    }

    public void setRows(List<PerfilM> rows) {
        this.rows = rows;
    }

    public PerfilM getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(PerfilM selectedRow) {
        this.selectedRow = selectedRow;
    }

    public List<PerfilMenuM> getPerfilMenuItems() {
        return perfilMenuItems;
    }

    public void setPerfilMenuItems(List<PerfilMenuM> perfilMenuItems) {
        this.perfilMenuItems = perfilMenuItems;
    }
    // </editor-fold>

}
