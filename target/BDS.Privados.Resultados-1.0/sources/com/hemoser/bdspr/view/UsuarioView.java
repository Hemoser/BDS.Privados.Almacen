/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hemoser.bdspr.view;

import com.hemoser.bdspr.model.UsuarioM;
import com.hemoser.bdspr.service.UsuarioService;
import com.hemoser.bdspr.util.Util;
import com.hemoser.bdspr.viewmodel.UsuarioViewModel;
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
@ManagedBean(name = "UsuarioView")
@ViewScoped
public class UsuarioView implements Serializable {

    private UsuarioM editItem;
    private List<UsuarioViewModel> rows;
    private UsuarioViewModel selectedRow;
    private String accion;
    private Integer fnuserid;

    @PostConstruct
    public void init() {
        try {
            accion = Util.getBase64Param("OP");
            switch (accion) {
                case "ADD":
                    editItem = new UsuarioM();
                    editItem.setFbactivo(true);
                    break;
                case "EDIT":
                    fnuserid = Util.tryParseInteger(Util.getBase64Param("ID"));
                    editItem = UsuarioService.selectUsuarioById(fnuserid);
                    break;
                default:
                    rows = UsuarioService.selectAll();
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(UsuarioView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void onRowUnselected(UnselectEvent event) {
        selectedRow = null;
    }

    public void saveAction() {
        if (accion.equals("ADD")) {
            add();
        } else {
            edit();
        }
    }

    public void add() {
        try {
            if (UsuarioService.validaUsername(editItem.getFcusername())) {
                if (UsuarioService.addUsuario(editItem)) {
                    Util.addInfoMessage("Se agregó el usuario con éxito");
                } else {
                    Util.addErrMessage("No se logró agregar el usuario ");
                }
                redirect();
            } else {
                Util.addErrMessage("El nombre de usuario ya existe.");
            }
        } catch (Exception ex) {
            Util.addExceptionMessage(ex);
            Logger.getLogger(UsuarioView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void edit() {
        try {
            if (UsuarioService.updateUsuario(editItem)) {
                Util.addInfoMessage("Se actualizó el usuario con éxito");
            } else {
                Util.addErrMessage("No se logró actualizar el usuario ");
            }
            redirect();
        } catch (Exception ex) {
            Util.addExceptionMessage(ex);
            Logger.getLogger(UsuarioView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void redirect() {
        Util.redirect("list.xhtml");
    }

    // <editor-fold defaultstate="collapsed" desc="Properties">
    public UsuarioM getEditItem() {
        return editItem;
    }

    public void setEditItem(UsuarioM editItem) {
        this.editItem = editItem;
    }

    public List<UsuarioViewModel> getRows() {
        return rows;
    }

    public void setRows(List<UsuarioViewModel> rows) {
        this.rows = rows;
    }

    public UsuarioViewModel getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(UsuarioViewModel selectedRow) {
        this.selectedRow = selectedRow;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    // </editor-fold>

}
