/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hemoser.bdspr.view;

import com.hemoser.bdspr.reporte.ReportTestController;
import com.hemoser.bdspr.util.Util;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Sergio
 */
@ManagedBean(name = "ReporteView")
@ViewScoped
public class ReporteView implements Serializable {

    @ManagedProperty("#{reportBean}")
    private ReportTestController reportesController;

    @PostConstruct
    public void init() {
        try {

        } catch (Exception ex) {
            Util.addExceptionMessage(ex);
            Logger.getLogger(ReporteView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String imprimeResultados() {
      /*  try {
            Integer institucionId = Util.tryParseInteger(Util.getParam("INSTID"));
            Integer status = Util.tryParseInteger(Util.getParam("STATUS"));
           Integer donadorPacienteId = Util.tryParseInteger(Util.getParam("DPACID"));
            return reportesController.imprimeResultados(institucionId, status, donadorPacienteId);
        } catch (Exception ex) {
            return ex.getMessage();
        }*/
      return "";
    }

    //<editor-fold defaultstate="collapsed" desc="Properties">
    public void setReportesController(ReportTestController reportesController) {
        this.reportesController = reportesController;
    }
    //</editor-fold>

}
