/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hemoser.bdspr.service;

import com.hemoser.bdspr.model.UsuarioLogInM;
import com.hemoser.bdspr.util.Util;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author f_per
 */
public class LogInService extends BaseService {
    // Revisado

    public static UsuarioLogInM validaUsuario(String fcusername, String fcpassword) {
        final String sql = "CALL sp_ValidarUsuario(?,?)";

        try (Connection conn = GetDataSource().getConnection(); CallableStatement cs = conn.prepareCall(sql);) {
            Util.setStringEqual(cs, "_fcusername", fcusername);
            Util.setStringEqual(cs, "_fcpassword", fcpassword);

            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    UsuarioLogInM item = new UsuarioLogInM();
                    item.setFnuserid(rs.getInt("fnuserid"));
                    item.setFcusername(rs.getString("fcusername"));
                    item.setFcpassword(rs.getString("fcpassword"));
                    item.setFnactivo(rs.getBoolean("fbactivo"));
                    item.setFcnombre(rs.getString("fcnombre"));
                    item.setFcpaterno(rs.getString("fcpaterno"));
                    item.setFcmaterno(rs.getString("fcmaterno"));
                    item.setPerfilid(rs.getInt("perfilid"));
                    return item;
                }
                return null;
            }
        } catch (Exception ex) {
            Logger.getLogger(LogInService.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("ERROR SQL: " + ex);
        }
    }

    public static String getUsername(Integer fnuserid) {
        final String sql = "SELECT u.fcusername FROM usuario u WHERE u.fnuserid = ?;";
        String fcusername = "";

        try (Connection conn = GetDataSource().getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setInt(1, fnuserid);
            ps.execute();
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    fcusername = rs.getString("fcusername");
                }
            }
        } catch (Exception se) {
            throw new RuntimeException("error SQL", se);
        }

        return fcusername;
    }

    public static void clearSessionActivity() {
        try (Connection conn = GetDataSource().getConnection();
                PreparedStatement ps = conn.prepareStatement("DELETE FROM current_session WHERE fnuserid = ?;")) {
            ps.setInt(1, Util.getUserId());
            ps.executeUpdate();
            String fcusername = getUsername(Util.getUserId());
        } catch (Exception ex) {
            Logger.getLogger(LogInService.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("ERROR SQL: " + ex);
        }
    }
    // Revisado

    public static boolean lastSessionActivity(Integer fnuserid) {
        try (Connection conn = GetDataSource().getConnection();
                CallableStatement cs = conn.prepareCall("{CALL spGetLastSessionActivity(?)}");) {
            cs.setInt("_UsuarioId", fnuserid);
            cs.execute();
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    return rs.getBoolean("RESULT");
                }
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(LogInService.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("ERROR SQL: " + ex);
        }
    }

    public static UsuarioLogInM validateCredentials(String username) {

        try (Connection conn = GetDataSource().getConnection(); CallableStatement cs = conn.prepareCall("{CALL spValidateUsuarioExt(?)}");) {
            Util.setStringEqual(cs, "_Username", username);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    UsuarioLogInM item = new UsuarioLogInM();
                    item.setFcusername(rs.getString("fcusername"));
                    item.setFcpassword(rs.getString("fcpassword"));
                    return item;
                }
                return null;
            }
        } catch (Exception se) {
            throw new RuntimeException("error SQL", se);
        }

    }
}
