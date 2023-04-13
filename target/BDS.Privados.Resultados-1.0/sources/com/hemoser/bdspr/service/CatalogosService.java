/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hemoser.bdspr.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author max
 */
@ManagedBean(name = "CatalogosService")
@SessionScoped
public class CatalogosService extends BaseService implements Serializable {

    public List<SelectItem> getUsuarios() {
        return getMenuList(
                "SELECT u.fnuserid, CONCAT_WS(' ',u.fcnombre,u.fcpaterno,u.fcmaterno) AS nombreAsesor FROM usuario u "
                + "JOIN usuario_perfil up ON u.fnuserid = up.usuarioid;");
    }

    public List<SelectItem> getPerfiles() {
        return getMenuList("SELECT p.perfilid, p.perfil  FROM perfil p ORDER BY p.perfil;");
    }

    public List<SelectItem> getMenuList(String sql, Object... args) {
        List<SelectItem> items = new ArrayList<>();
        try (Connection conn = GetDataSource().getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
            int i = 1;
            for (Object arg : args) {
                if (arg instanceof Date) {
                    ps.setTimestamp(i++, new Timestamp(((Date) arg).getTime()));
                } else if (arg instanceof Integer) {
                    ps.setInt(i++, (Integer) arg);
                } else if (arg instanceof Long) {
                    ps.setLong(i++, (Long) arg);
                } else if (arg instanceof Double) {
                    ps.setDouble(i++, (Double) arg);
                } else if (arg instanceof Float) {
                    ps.setFloat(i++, (Float) arg);
                } else {
                    ps.setString(i++, (String) arg);
                }
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    items.add(new SelectItem(rs.getInt(1), rs.getString(2)));
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(CatalogosService.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("ERROR SQL", ex);
        }
        return items;
    }

}
