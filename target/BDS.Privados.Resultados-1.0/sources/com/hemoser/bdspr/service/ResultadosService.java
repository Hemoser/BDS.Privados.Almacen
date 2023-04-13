/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hemoser.bdspr.service;

import com.hemoser.bdspr.model.AreaServicioM;
import com.hemoser.bdspr.model.InstitucionM;
import com.hemoser.bdspr.util.Util;
import com.hemoser.bdspr.viewmodel.FiltrosResultadosVM;
import com.hemoser.bdspr.viewmodel.ResultadosVM;
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
public class ResultadosService extends BaseService implements Serializable {

    public static List<ResultadosVM> getResultados(FiltrosResultadosVM itemf) {
        final String sql = "CALL spGetResultados(?,?,?,?,?,?,?,?,?,?,?,?);";

        List<ResultadosVM> items = new ArrayList<>();

        try (Connection conn = GetDataSource().getConnection(); CallableStatement cs = conn.prepareCall(sql);) {
            Util.setIntegerParameter(cs, "_UserId", Util.getUserId());
            Util.setIntegerParameter(cs, "_InstitucionId", itemf.getInstitucionId());
            Util.setStringLike(cs, "_Nombre", itemf.getNombre());
            Util.setStringLike(cs, "_ApPaterno", itemf.getPaterno());
            Util.setStringLike(cs, "_ApMaterno", itemf.getMaterno());
            Util.setStringLike(cs, "_Analisis", itemf.getAnalisis());
            Util.setIntegerParameter(cs, "_Status", itemf.getStatus());
            Util.setDateParameter(cs, "_FechaOrden_Desde", itemf.getFod());
            Util.setDateParameter(cs, "_FechaOrden_Hasta", itemf.getFoh());
            Util.setDateParameter(cs, "_FechaResultado_Desde", itemf.getFrd());
            Util.setDateParameter(cs, "_FechaResultado_Hasta", itemf.getFrh());
            Util.setStringLike(cs, "_Servicio", itemf.getServicio());

            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    ResultadosVM item = new ResultadosVM();
                    item.setResultadoId(rs.getInt("resultadoId"));
                    item.setFechaOrden(rs.getTimestamp("fechaOrden"));
                    item.setNombre(rs.getString("nombre"));
                    item.setAnalisis(rs.getString("analisis"));
                    item.setResultado(rs.getString("resultado"));
                    item.setUsuarioValida(rs.getString("usuarioValida"));
                    item.setMatriculaValida(rs.getString("matriculaValida"));
                    item.setFechaValida(rs.getTimestamp("fechaValida"));
                    item.setTipo(rs.getString("tipo"));
                    item.setDonadorPacienteId(rs.getInt("donadorPacienteId"));
                    item.setSerologiaId(rs.getInt("serologiaId"));
                    item.setStatus(rs.getInt("status"));
                    items.add(item);
                }
            }
        } catch (Exception se) {
            throw new RuntimeException("Error SQL: " + se);
        }
        return items;
    }

    public static List<AreaServicioM> getServicios() {
        final String sql = "CALL spGetServicios();";

        List<AreaServicioM> items = new ArrayList<>();

        try (Connection conn = GetDataSource().getConnection(); CallableStatement cs = conn.prepareCall(sql);) {
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    AreaServicioM item = new AreaServicioM();
                    item.setFncveareaservicio(rs.getInt("fncveareaservicio"));
                    item.setFcareaservicionombre(rs.getString("fcareaservicionombre"));
                    items.add(item);
                }
            }
        } catch (Exception se) {
            throw new RuntimeException("Error SQL: " + se);
        }
        return items;
    }

    public static List<InstitucionM> getInstituciones() {
        final String sql = "CALL spGetInstituciones();";

        List<InstitucionM> items = new ArrayList<>();

        try (Connection conn = GetDataSource().getConnection(); CallableStatement cs = conn.prepareCall(sql);) {
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    InstitucionM item = new InstitucionM();
                    item.setFncveinstitucion(rs.getInt("fncveinstitucion"));
                    item.setFcinstitucion(rs.getString("fcinstitucion"));
                    items.add(item);
                }
            }
        } catch (Exception se) {
            throw new RuntimeException("Error SQL: " + se);
        }
        return items;
    }
}
