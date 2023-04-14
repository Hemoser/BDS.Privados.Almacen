/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hemoser.bdspr.service;

import com.hemoser.bdspr.model.ComponentesM;
import com.hemoser.bdspr.model.InstitucionM;
import com.hemoser.bdspr.util.Util;
import com.hemoser.bdspr.viewmodel.FiltrosAlmacenVM;
import com.hemoser.bdspr.viewmodel.AlmacenVM;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ricardo SR
 */
public class AlmacenService extends BaseService implements Serializable {

    public static List<AlmacenVM> getAlmacen(FiltrosAlmacenVM itemf) {
        final String sql = "CALL spGetAlmacen(?,?,?,?,?);";

        List<AlmacenVM> items = new ArrayList<>();

        try (Connection conn = GetDataSource().getConnection(); CallableStatement cs = conn.prepareCall(sql);) {
            Util.setIntegerParameter(cs, "_UnidadId", itemf.getUnidadId());
            Util.setStringLike(cs, "_Nombre", itemf.getNombre());
            Util.setDateParameter(cs, "_Caducidad", itemf.getFechaCaducidad());
            Util.setStringLike(cs, "_Fraccion", itemf.getComponente());
            Util.setIntegerParameter(cs, "_InstitucionId", itemf.getInstitucionId());

            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    AlmacenVM item = new AlmacenVM();
                    item.setUnidad(rs.getInt("unidadId"));
                    item.setComponente(rs.getString("fraccion"));
                    item.setFechaExtraccion(rs.getTimestamp("fechaExtraccion"));
                    item.setFechaCaducidad(rs.getTimestamp("fechaCaducidad"));
                    item.setVolumen(rs.getInt("volumen"));
                    item.setDonadorId(rs.getInt("donadorId"));
                    item.setNombre(rs.getString("nombre"));
                    item.setPediatrica(rs.getBoolean("pediatrica"));
                    item.setPool(rs.getBoolean("pool"));
                    item.setGpoSang(rs.getString("gpoSang"));
                    items.add(item);
                }
            }
        } catch (Exception se) {
            throw new RuntimeException("Error SQL: " + se);
        }
        return items;
    }

    public static List<ComponentesM> getComponentes() {
        final String sql = "CALL spGetComponentes();";

        List<ComponentesM> items = new ArrayList<>();

        try (Connection conn = GetDataSource().getConnection(); CallableStatement cs = conn.prepareCall(sql);) {
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    ComponentesM item = new ComponentesM();
                    item.setFraccionId(rs.getInt("fraccionId"));
                    item.setFraccion(rs.getString("fraccion"));
                    items.add(item);
                }
            }
        } catch (Exception se) {
            throw new RuntimeException("Error SQL: " + se);
        }
        return items;
    }
}
