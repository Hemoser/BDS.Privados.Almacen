/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hemoser.bdspr.service;

import com.hemoser.bdspr.model.PerfilM;
import com.hemoser.bdspr.model.PerfilMenuHorizontalM;
import com.hemoser.bdspr.model.PerfilMenuM;
import static com.hemoser.bdspr.service.BaseService.GetDataSource;
import com.hemoser.bdspr.util.Util;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sergio
 */
public class GeneralService extends BaseService {

    public static byte[] getImagenPerfil(Integer fnuserid) {
        final String sql = "select imagen from usuario_imagen WHERE fnuserid = ? ";
        byte[] result = null;
        try (Connection conn = GetDataSource().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, fnuserid);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result = rs.getBytes(1);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(GeneralService.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("error SQL", ex);
        }
        return result;
    }

    public static String getReporteadorUrl() {
        final String sql = "SELECT url_reporteador FROM parametro p WHERE p.fncveparametro = 1;";
        String result = "";
        try (Connection conn = GetDataSource().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result = rs.getString("url_reporteador");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(GeneralService.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("error SQL", ex);
        }
        return result;
    }

    public static void updateCurrentSession() {
        try (Connection conn = GetDataSource().getConnection();
                PreparedStatement ps = conn
                        .prepareStatement("REPLACE INTO current_session (fnuserid, fdactividad) VALUES (?, NOW());")) {
            ps.setInt(1, Util.getUserId());
            ps.execute();
        } catch (Exception ex) {
            Logger.getLogger(GeneralService.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("ERROR SQL: " + ex);
        }
    }

    public static List<PerfilMenuHorizontalM> GetPerfilMenuHorizontal(int perfilId, int menuId) {
        final String sql = "SELECT mh.id_menu_horizontal, m.menuid, m.label, mh.descripcion, IFNULL(pmh.habilitar, 1) AS habilitar\n"
                + "FROM menu_horizontal mh\n"
                + "JOIN menu m ON m.menuid = mh.menuid\n"
                + "LEFT JOIN perfil_menu_horizontal pmh ON pmh.perfilid = ? AND pmh.id_menu_horizontal = mh.id_menu_horizontal\n"
                + "WHERE mh.menuid = ?;";
        try (Connection conn = GetDataSource().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            Util.setIntegerParameter(ps, 1, perfilId);
            Util.setIntegerParameter(ps, 2, menuId);
            List<PerfilMenuHorizontalM> items = new ArrayList();
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    PerfilMenuHorizontalM i = new PerfilMenuHorizontalM();
                    i.setMenuHorizontalId(rs.getInt("id_menu_horizontal"));
                    i.setPerfilId(perfilId);
                    i.setMenuId(rs.getInt("menuid"));
                    i.setLabel(rs.getString("label"));
                    i.setDescripcion(rs.getString("descripcion"));
                    i.setHabilitar(rs.getBoolean("habilitar"));
                    items.add(i);
                }
            }
            return items;
        } catch (Exception ex) {
            Logger.getLogger(GeneralService.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("ERROR SQL: " + ex);
        }
    }

    public static boolean updateMenuHorizontal(PerfilMenuHorizontalM pmh) {
        final String sql = "REPLACE INTO perfil_menu_horizontal (perfilid, id_menu_horizontal, habilitar) VALUES (?, ?, ?);";
        try (Connection conn = GetDataSource().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            Util.setIntegerParameter(ps, 1, pmh.getPerfilId());
            Util.setIntegerParameter(ps, 2, pmh.getMenuHorizontalId());
            Util.setBoolParameter(ps, 3, pmh.isHabilitar());
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            Logger.getLogger(GeneralService.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("ERROR SQL: " + ex);
        }
    }

    public static boolean updateImagenPerfil(int fnuserid, byte[] imagen) {
        String sql = "{call spI_imagenUsuario(?,?)}";
        boolean realizado = false;
        try (Connection conn = GetDataSource().getConnection();) {
            CallableStatement cs = conn.prepareCall(sql);
            Util.setIntegerParameter(cs, "_fnuserid", fnuserid);
            cs.setBytes("_imagen", imagen);
            cs.execute();
            realizado = true;
        } catch (Exception ex) {
            Logger.getLogger(GeneralService.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("ERROR SQL: " + ex);
        }
        return realizado;
    }

    public static List<PerfilMenuM> selectPerfilMenu() {
        List<PerfilMenuM> items = new ArrayList<>();
        try (Connection conn = GetDataSource().getConnection();
                CallableStatement cs = conn.prepareCall("{CALL spGetPerfilMenuByUsuarioId(?)}");) {
            cs.setInt("_fnuserid", Util.getUserId());
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    PerfilMenuM item = new PerfilMenuM();
                    item.setMenuid(rs.getString("menuid"));
                    item.setActivo(rs.getBoolean("activo"));
                    items.add(item);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(GeneralService.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("ERROR SQL: " + ex);
        }
        return items;
    }

    public static List<PerfilM> selectPerfiles() {

        try (Connection conn = GetDataSource().getConnection();) {
            CallableStatement cs = conn.prepareCall("{CALL spGetPerfiles()}");
            List<PerfilM> items = new ArrayList();
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    PerfilM item = new PerfilM();
                    item.setPerfilid(rs.getInt("perfilid"));
                    item.setPerfil(rs.getString("perfil"));
                    item.setActivo(rs.getBoolean("fbactivo"));
                    items.add(item);
                }
            }
            return items;
        } catch (Exception ex) {
            Logger.getLogger(GeneralService.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("ERROR SQL: " + ex);
        }
    }

    public static PerfilM selectPerfilById(int perfilid) {
        try (Connection conn = GetDataSource().getConnection();) {
            CallableStatement cs = conn.prepareCall("{CALL spGetPerfilById(?)}");
            Util.setIntegerParameter(cs, "_perfilid", perfilid);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    PerfilM item = new PerfilM();
                    item.setPerfilid(rs.getInt("perfilid"));
                    item.setPerfil(rs.getString("perfil"));
                    item.setActivo(rs.getBoolean("fbactivo"));
                    return item;
                }
            }
            return null;
        } catch (Exception ex) {
            Logger.getLogger(GeneralService.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("ERROR SQL: " + ex);
        }
    }

    public static List<PerfilMenuM> selectPerfilMenuByPerfilId(Integer perfilid) {
        try (Connection conn = GetDataSource().getConnection();) {
            CallableStatement cs = conn.prepareCall("{CALL spGetPerfilMenuByPerfilId(?)}");
            Util.setIntegerParameter(cs, "_perfilid", perfilid);
            List<PerfilMenuM> items = new ArrayList();
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    PerfilMenuM item = new PerfilMenuM();
                    item.setPerfilmenuid(rs.getObject("perfilmenuid", Integer.class));
                    item.setPerfilid(rs.getObject("perfilid", Integer.class));
                    item.setMenuid(rs.getString("menuid"));
                    item.setNombre(rs.getString("nombre"));
                    item.setActivo(rs.getBoolean("activo"));
                    item.setTipo(rs.getString("tipo"));
                    items.add(item);
                }
            }
            return items;
        } catch (Exception ex) {
            Logger.getLogger(GeneralService.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("ERROR SQL: " + ex);
        }
    }

    public static Boolean addPerfil(PerfilM item) {
        Boolean realizado = false;
        try (Connection conn = BaseService.GetDataSource().getConnection();
                CallableStatement cs = conn.prepareCall("{CALL spI_Perfil(?,?)}");) {
            Util.setStringEqual(cs, "_perfil", item.getPerfil());
            Util.setBoolParameter(cs, "_fbactivo", item.isActivo());
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    item.setPerfilid(rs.getInt("ID"));
                }
            }
            realizado = true;
        } catch (Exception ex) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("ERROR SQL: " + ex);
        }
        return realizado;
    }

    public static Boolean updatePerfil(PerfilM item) {
        Boolean realizado = false;

        try (Connection conn = BaseService.GetDataSource().getConnection();
                CallableStatement cs = conn.prepareCall("{CALL spU_Perfil(?,?,?)}");) {
            Util.setStringEqual(cs, "_perfil", item.getPerfil());
            Util.setBoolParameter(cs, "_fbactivo", item.isActivo());
            Util.setIntegerParameter(cs, "_perfilid", item.getPerfilid());
            cs.execute();
            realizado = true;
        } catch (Exception ex) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("ERROR SQL: " + ex);
        }
        return realizado;
    }

    public static void addPerfilMenuItems(int perfilid, List<PerfilMenuM> items) throws Exception {
        try (Connection conn = BaseService.GetDataSource().getConnection()) {
            conn.setAutoCommit(false);
            try {
                for (int i = 0; i < items.size(); i++) {
                    try (CallableStatement cs = conn.prepareCall("{CALL spI_PerfilMenu(?, ?, ?)}");) {
                        Util.setIntegerParameter(cs, "_perfilid", perfilid);
                        Util.setStringEqual(cs, "_menuid", items.get(i).getMenuid());
                        Util.setBoolParameter(cs, "_activo", items.get(i).isActivo());
                        cs.execute();
                    }
                    conn.commit();
                }
            } catch (Exception ex) {
                conn.rollback();
                Logger.getLogger(GeneralService.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException("ERROR SQL: " + ex);
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }

}
