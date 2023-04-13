/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hemoser.bdspr.view;

import com.hemoser.bdspr.model.AreaServicioM;
import com.hemoser.bdspr.model.InstitucionM;
import com.hemoser.bdspr.reporte.ReportTestController;
import com.hemoser.bdspr.service.ResultadosService;
import com.hemoser.bdspr.util.Util;
import com.hemoser.bdspr.viewmodel.FiltrosResultadosVM;
import com.hemoser.bdspr.viewmodel.ResultadosVM;
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
@ManagedBean(name = "ResultadosView")
public class ResultadosView implements Serializable {

    private List<ResultadosVM> items;
    private FiltrosResultadosVM filtros;
    private List<AreaServicioM> itemsServicios;
    private List<InstitucionM> itemsInstituciones;
    private InstitucionM itemInstitucion;
    private String institucionLabel;
    private String rptInstitucionLabel;
    private Integer rptInstitucionId;
    private Integer rptStatus;
    private Integer rptIdPaciente;

    @ManagedProperty("#{reportBean}")
    private ReportTestController reportesController;

    @PostConstruct
    public void init() {
        limpiar();
        itemsServicios = ResultadosService.getServicios();
        itemsInstituciones = ResultadosService.getInstituciones();
    }

    public void consultar() {
        items = ResultadosService.getResultados(filtros);
    }

    public void limpiar() {
        filtros = new FiltrosResultadosVM();
        consultar();
    }

    public void selectInstitucion() {
        filtros.setInstitucionId(itemInstitucion.getFncveinstitucion());
        institucionLabel = itemInstitucion.getFcinstitucion();
    }

    public void selectPrtInstitucion() {
        rptInstitucionId = itemInstitucion.getFncveinstitucion();
        rptInstitucionLabel = itemInstitucion.getFcinstitucion();
    }

    public void imprimirResultados() {
        try {
            reportesController.imprimeResultados(rptInstitucionId, rptStatus, rptIdPaciente);
        } catch (Exception ex) {
            Util.addExceptionMessage(ex);
        }
    }
//<editor-fold defaultstate="collapsed" desc="Getters - Setters">

    public void setReportesController(ReportTestController reportesController) {
        this.reportesController = reportesController;
    }

    public List<ResultadosVM> getItems() {
        return items;
    }

    public void setItems(List<ResultadosVM> items) {
        this.items = items;
    }

    public FiltrosResultadosVM getFiltros() {
        return filtros;
    }

    public void setFiltros(FiltrosResultadosVM filtros) {
        this.filtros = filtros;
    }

    public List<AreaServicioM> getItemsServicios() {
        return itemsServicios;
    }

    public void setItemsServicios(List<AreaServicioM> itemsServicios) {
        this.itemsServicios = itemsServicios;
    }

    public List<InstitucionM> getItemsInstituciones() {
        return itemsInstituciones;
    }

    public void setItemsInstituciones(List<InstitucionM> itemsInstituciones) {
        this.itemsInstituciones = itemsInstituciones;
    }

    public InstitucionM getItemInstitucion() {
        return itemInstitucion;
    }

    public void setItemInstitucion(InstitucionM itemInstitucion) {
        this.itemInstitucion = itemInstitucion;
    }

    public String getInstitucionLabel() {
        return institucionLabel;
    }

    public void setInstitucionLabel(String institucionLabel) {
        this.institucionLabel = institucionLabel;
    }

    public String getRptInstitucionLabel() {
        return rptInstitucionLabel;
    }

    public void setRptInstitucionLabel(String rptInstitucionLabel) {
        this.rptInstitucionLabel = rptInstitucionLabel;
    }

    public Integer getRptInstitucionId() {
        return rptInstitucionId;
    }

    public void setRptInstitucionId(Integer rptInstitucionId) {
        this.rptInstitucionId = rptInstitucionId;
    }

    public Integer getRptStatus() {
        return rptStatus;
    }

    public void setRptStatus(Integer rptStatus) {
        this.rptStatus = rptStatus;
    }

    public Integer getRptIdPaciente() {
        return rptIdPaciente;
    }

    public void setRptIdPaciente(Integer rptIdPaciente) {
        this.rptIdPaciente = rptIdPaciente;
    }
//</editor-fold>

}
