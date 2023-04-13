/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hemoser.bdspr.service;

import com.hemoser.bdspr.model.UsuarioM;
import com.hemoser.bdspr.util.Util;
import com.hemoser.bdspr.viewmodel.UsuarioViewModel;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Sergio
 */
@ManagedBean(name = "UsuarioService", eager = true)
@SessionScoped
public class UsuarioService extends BaseService implements Serializable {

    public static List<UsuarioM> selectAllUsuario() {
        final String sql = "CALL spGetUsuarios();";
        List<UsuarioM> items = new ArrayList<>();

        try (Connection conn = GetDataSource().getConnection();
                CallableStatement cs = conn.prepareCall(sql);) {
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    UsuarioM item = new UsuarioM();
                    item.setFnuserid(rs.getInt("fnuserid"));
                    item.setFcusername(rs.getString("fcusername"));
                    item.setFcpassword(rs.getString("fcpassword"));
                    item.setFbactivo(rs.getBoolean("fbactivo"));
                    item.setFcnombre(rs.getString("fcnombre"));
                    item.setFcpaterno(rs.getString("fcpaterno"));
                    item.setFcmaterno(rs.getString("fcmaterno"));
                    items.add(item);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("ERROR SQL: " + ex);
        }
        return items;
    }

    public static List<UsuarioViewModel> selectAll() {
        List<UsuarioViewModel> items = new ArrayList<>();
        try (Connection conn = GetDataSource().getConnection();
                CallableStatement cs = conn.prepareCall("{CALL spGetUsuarios()}");) {
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    UsuarioViewModel item = new UsuarioViewModel();
                    item.setId(rs.getInt("id"));
                    item.setUsername(rs.getString("username"));
                    item.setNombre((rs.getString("nombre")));
                    item.setPerfil((rs.getString("perfil")));
                    item.setActivo((rs.getBoolean("activo")));
                    items.add(item);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("ERROR SQL: " + ex);
        }
        return items;
    }

    public static UsuarioM selectUsuarioById(Integer fnuserid) {
        try (Connection conn = GetDataSource().getConnection();
                CallableStatement cs = conn.prepareCall("{CALL spGetUsuarioById(?)}");) {
            Util.setIntegerParameter(cs, "_fnuserid", fnuserid);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    UsuarioM item = new UsuarioM();
                    item.setFnuserid(rs.getInt("fnuserid"));
                    item.setFcusername(rs.getString("fcusername"));
                    item.setFcpassword(rs.getString("fcpassword"));
                    item.setFbactivo(rs.getBoolean("fbactivo"));
                    item.setFcnombre(rs.getString("fcnombre"));
                    item.setFcpaterno(rs.getString("fcpaterno"));
                    item.setFcmaterno(rs.getString("fcmaterno"));
                    item.setPerfilid(rs.getObject("perfilid", Integer.class));
                    item.setFbconfidenciales(rs.getBoolean("fbconfidenciales"));
                    return item;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("ERROR SQL: " + ex);
        }
        return null;
    }

    public static boolean validaUsername(String fcusername) {
        try (Connection conn = BaseService.GetDataSource().getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM usuario u WHERE u.fcusername = ?;");) {
            ps.setString(1, fcusername);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return false;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("ERROR SQL: " + ex);
        }
        return true;
    }

    public static Boolean addUsuario(UsuarioM usuario) {
        Boolean realizado = false;
        try (Connection conn = BaseService.GetDataSource().getConnection();
                CallableStatement cs = conn.prepareCall("{CALL spI_Usuario(?,?,?,?,?,?,?,?)}");) {
            Util.setStringEqual(cs, "_fcusername", usuario.getFcusername());
            Util.setStringEqual(cs, "_fcnombre", usuario.getFcnombre());
            Util.setStringEqual(cs, "_fcpassword", usuario.getFcpassword());
            Util.setStringEqual(cs, "_fcpaterno", usuario.getFcpaterno());
            Util.setStringEqual(cs, "_fcmaterno", usuario.getFcmaterno());
            Util.setBoolParameter(cs, "_fbactivo", usuario.getFbactivo());
            Util.setIntegerParameter(cs, "_perfilid", usuario.getPerfilid());
            Util.setBoolParameter(cs, "_fbconfidenciales", usuario.getFbconfidenciales());
            cs.execute();
            realizado = true;
        } catch (Exception ex) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("ERROR SQL: " + ex);
        }
        return realizado;
    }

    public static boolean updateUsuario(UsuarioM usuario) {
        Boolean realizado = false;
        try (Connection conn = BaseService.GetDataSource().getConnection();
                CallableStatement cs = conn.prepareCall("{CALL spU_Usuario(?,?,?,?,?,?,?,?,?)}");) {
            Util.setIntegerParameter(cs, "_fnuserid", usuario.getFnuserid());
            Util.setStringEqual(cs, "_fcusername", usuario.getFcusername());
            Util.setStringEqual(cs, "_fcnombre", usuario.getFcnombre());
            Util.setStringEqual(cs, "_fcpassword", usuario.getFcpassword());
            Util.setStringEqual(cs, "_fcpaterno", usuario.getFcpaterno());
            Util.setStringEqual(cs, "_fcmaterno", usuario.getFcmaterno());
            Util.setBoolParameter(cs, "_fbactivo", usuario.getFbactivo());
            Util.setIntegerParameter(cs, "_perfilid", usuario.getPerfilid());
            Util.setBoolParameter(cs, "_fbconfidenciales", usuario.getFbconfidenciales());
            cs.execute();
            realizado = true;
        } catch (Exception ex) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("ERROR SQL: " + ex);
        }
        return realizado;
    }

    public static void updateUsuarioPassword(UsuarioM usuario) {
        try (Connection conn = BaseService.GetDataSource().getConnection();
                CallableStatement cs = conn.prepareCall("{CALL spU_UsuarioPassword(?, ?)}");) {
            Util.setIntegerParameter(cs, "_fnuserid", usuario.getFnuserid());
            Util.setStringEqual(cs, "_fcpassword", usuario.getFcpassword());
            cs.execute();
        } catch (Exception ex) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("ERROR SQL: " + ex);
        }
    }
}
