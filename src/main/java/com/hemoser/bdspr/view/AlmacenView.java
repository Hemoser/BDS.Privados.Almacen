/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hemoser.bdspr.view;

import com.hemoser.bdspr.model.ComponentesM;
import com.hemoser.bdspr.model.InstitucionM;
import com.hemoser.bdspr.reporte.ReportTestController;
import com.hemoser.bdspr.service.AlmacenService;
import com.hemoser.bdspr.util.Util;
import com.hemoser.bdspr.viewmodel.FiltrosAlmacenVM;
import com.hemoser.bdspr.viewmodel.AlmacenVM;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Ricardo SR
 */
@ViewScoped
@ManagedBean(name = "AlmacenView")
public class AlmacenView implements Serializable {

    private List<AlmacenVM> items;
    private FiltrosAlmacenVM filtros;
    private List<ComponentesM> itemsComp;
    private Integer rptComponenteId;

    @ManagedProperty("#{reportBean}")
    private ReportTestController reportesController;

    @PostConstruct
    public void init() {
        limpiar();
        itemsComp = AlmacenService.getComponentes();
    }

    public void consultar() {
        items = AlmacenService.getAlmacen(filtros);
    }

    public void limpiar() {
        filtros = new FiltrosAlmacenVM();
        consultar();
    }

    public void imprimirAlmacen() {
        try {
            reportesController.imprimeBolsasAlmacen(rptComponenteId);
        } catch (Exception ex) {
            Util.addExceptionMessage(ex);
        }
    }
//<editor-fold defaultstate="collapsed" desc="Getters - Setters">

    public void setReportesController(ReportTestController reportesController) {
        this.reportesController = reportesController;
    }

    public List<AlmacenVM> getItems() {
        return items;
    }

    public void setItems(List<AlmacenVM> items) {
        this.items = items;
    }

    public FiltrosAlmacenVM getFiltros() {
        return filtros;
    }

    public void setFiltros(FiltrosAlmacenVM filtros) {
        this.filtros = filtros;
    }

    public Integer getRptComponenteId() {
        return rptComponenteId;
    }

    public void setRptComponenteId(Integer rptComponenteId) {
        this.rptComponenteId = rptComponenteId;
    }

    public List<ComponentesM> getItemsComp() {
        return itemsComp;
    }

    public void setItemsComp(List<ComponentesM> itemsComp) {
        this.itemsComp = itemsComp;
    }
//</editor-fold>

}
