/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hemoser.bdspr.service;

import com.hemoser.bdspr.model.DashboardM;
import static com.hemoser.bdspr.service.BaseService.GetDataSource;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author f_per
 */
public class DashboardService extends BaseService implements Serializable {

    public static List<List<DashboardM>> getTareas() {
        final String sql1 = "CALL spGetTareasByDay();";
        final String sql2 = "CALL spGetTareasByMonth();";
        final String sql3 = "CALL spGetTareasByYear();";
        List<List<DashboardM>> itemofitems = new ArrayList();
        List<DashboardM> items = new ArrayList();
        try (Connection conn = GetDataSource().getConnection();) {
            try (CallableStatement cs = conn.prepareCall(sql1);) {
                try (ResultSet rs = cs.executeQuery()) {
                    while (rs.next()) {
                        DashboardM item = new DashboardM();
                        item.setStatus(rs.getInt("status"));
                        item.setCount(rs.getInt("count"));
                        items.add(item);
                    }
                }
            }
            itemofitems.add(items);
            items = new ArrayList();
            try (CallableStatement cs = conn.prepareCall(sql2);) {
                try (ResultSet rs = cs.executeQuery()) {
                    while (rs.next()) {
                        DashboardM item = new DashboardM();
                        item.setStatus(rs.getInt("status"));
                        item.setCount(rs.getInt("count"));
                        items.add(item);
                    }
                }
            }
            itemofitems.add(items);
            items = new ArrayList();
            try (CallableStatement cs = conn.prepareCall(sql3);) {
                try (ResultSet rs = cs.executeQuery()) {
                    while (rs.next()) {
                        DashboardM item = new DashboardM();
                        item.setStatus(rs.getInt("status"));
                        item.setCount(rs.getInt("count"));
                        items.add(item);
                    }
                }
            }
            itemofitems.add(items);
        } catch (Exception ex) {
            Logger.getLogger(DashboardService.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("error SQL", ex);
        }

        return itemofitems;
    }

    public static Integer getRefreshRate() {
        final String sql = "CALL spGetRefreshRate();";
        Integer refreshRate = 300;
        try (Connection conn = GetDataSource().getConnection(); CallableStatement cs = conn.prepareCall(sql);) {
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    refreshRate = rs.getInt("fnminutosrefresh") * 60; //Convertir minutos a segundos
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DashboardService.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("error SQL", ex);
        }

        return refreshRate;
    }

}
