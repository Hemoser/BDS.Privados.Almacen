/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hemoser.bdspr.filter;

import com.hemoser.bdspr.service.PerfilService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author max
 */
@WebFilter("*")
public class PageFilter implements Filter {

    final Map<String, String> pages = new HashMap<String, String>() {
        {
            put("/dashboard", "mi_dashboard");
            put("/rendimientos/rendimiento", "mi_rendimiento");
            put("/tickets/generar_ticket", "mi_crear_ticket");
            put("/tickets/tickets", "mi_mis_tickets");
            put("/tareas/crear_tarea", "mi_crear_tarea");
            put("/tareas/tareas", "mi_control_tareas");
            put("/actividades/actividades", "mi_actividades");
            put("/actividades/reportar_actividad", "mi_reportar_actividad");
            put("/sistema/usuarios", "mi_usuarios");
            put("/sistema/perfiles", "mi_perfiles");
            put("/sistema/estados", "estados");
            put("/sistema/instituciones", "instituciones");
            put("/sistema/tipo_tareas", "tipo-tareas");
            put("/reporteador", "si_reporteador");
            put("/rendimientos/delegacion", "ren_delegacion");
            put("/rendimientos/hospital", "ren_hospital");
            put("/rendimientos/tipo_tareas", "ren_tareas");
            put("/sistema/configuracion", "configuracion");
            put("/inventario/inventario", "mi_inventario");
            put("/inventario/area_hospital", "inv_area_hospital");
            put("/inventario/articulos", "inv_articulos");
            put("/inventario/inventario_salida", "mi_inventario_salida");
            put("/inventario/modelo", "inv_modelo");
            put("/inventario/proveedores", "inv_proveedores");
            put("/insumos/inventario", "ins_inventario");
            put("/insumos/insumo", "ins_insumos");
            put("/insumos/lote", "ins_lotes");

        }
    };

    @Override
    public void init(FilterConfig fc) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        String[] sites = {"", "/login.xhtml", "/RES_NOT_FOUND", "/faces", "css", "/ibb/javax.faces.resource"};
        String[] dirs = {"/javax.faces.resource", "login", "preguntasFrecuentes", "archivos", "popup", "pub",
            "/login.xhtml"};

        HttpServletRequest req = (HttpServletRequest) sr;
        HttpServletResponse res = (HttpServletResponse) sr1;
        HttpSession session = req.getSession(false);

        String path = req.getRequestURI().substring(req.getContextPath().length()).replaceAll("[/]+$", "");
        boolean loggedIn = ((session != null) && session.getAttribute("currentUser") != null);
        boolean continuar = false;

        if (!loggedIn) {
            for (String site : sites) {
                if (path.equals(site)) {
                    continuar = true;
                    break;
                }
            }

            if (!continuar) {
                for (String dir : dirs) {
                    if (path.matches("(?i)" + dir + ".*")) {
                        continuar = true;
                        break;
                    }
                }
            }

            if (path.isEmpty()) {
                continuar = false;
            }
        }

        if (!loggedIn && !continuar) {
            res.sendRedirect(req.getContextPath() + "/login.xhtml");
        } else {
            String tempPath = path.replace(".xhtml", "");
            // ANTIGUA FORMA DE COMPROBAR LAS RUTAS, ES NECESARIO REFACTORIZAR LAS RUTAS
            // PARA ELIMINARLO
            if (loggedIn && pages.containsKey(tempPath)) {
                PerfilService service = (PerfilService) session.getAttribute("perfilService");
                if (service != null) {
                    if (!service.menuIsActive(pages.get(tempPath))) {
                        res.sendRedirect(req.getContextPath() + "/unauthorized.xhtml");
                        return;
                    }
                }
            } // NUEVA FORMA DE COMPARAR LAS RUTAS DEBERÍA. CUANDO SE REFACTORIZEN TODAS LAS RUTAS EN CARPETAS SE VA A ELIMINAR EL IF DE ARRIBA
            else if (loggedIn) {
                tempPath = path.substring(0, path.lastIndexOf("/"));
                if (pages.containsKey(tempPath)) {
                    PerfilService service = (PerfilService) session.getAttribute("perfilService");
                    if (service != null) {
                        if (!service.menuIsActive(pages.get(tempPath))) {
                            res.sendRedirect(req.getContextPath() + "/unauthorized.xhtml");
                            return;
                        }
                    }
                }
            }
            fc.doFilter(req, res);
        }
    }

    @Override
    public void destroy() {
    }

}
