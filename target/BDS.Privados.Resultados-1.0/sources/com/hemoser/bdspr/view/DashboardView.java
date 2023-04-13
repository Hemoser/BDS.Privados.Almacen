/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hemoser.bdspr.view;

import com.hemoser.bdspr.model.DashboardM;
import com.hemoser.bdspr.service.DashboardService;
import com.hemoser.bdspr.util.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;

/**
 *
 * @author f_per
 */
@ManagedBean(name = "DashboardView")
@ViewScoped
public class DashboardView implements Serializable {

    @PostConstruct
    public void init() {
        try {

        } catch (Exception ex) {
            Util.addExceptionMessage(ex);
            Logger.getLogger(DashboardView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//<editor-fold defaultstate="collapsed" desc="Getters - Setters">  
//</editor-fold>
}
