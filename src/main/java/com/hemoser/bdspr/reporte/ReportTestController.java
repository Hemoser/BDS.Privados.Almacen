package com.hemoser.bdspr.reporte;

import com.hemoser.bdspr.service.BaseService;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.servlet.ServletOutputStream;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import com.hemoser.bdspr.util.Util;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import javax.annotation.PostConstruct;

/**
 *
 * @author aldo0
 */
@ManagedBean(name = "reportBean")
@ViewScoped
public class ReportTestController extends BaseService implements Serializable {

    @PostConstruct
    public void init() {

    }

    public void printReportToBrowser(Map<String, Object> pars, String report) throws Exception {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
        response.reset();
        try {
            String path;
            path = "C:\\ibbapp\\jasper\\" + report + ".jasper";
            InputStream reportStream = Files.newInputStream(Paths.get(path));
            try (ServletOutputStream servletOutputStream = response.getOutputStream();
                    Connection conn = GetDataSource().getConnection();) {
                JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, pars, conn);
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition", "inline;filename=" + report + ".pdf");
                servletOutputStream.flush();
            }
        } catch (IOException | SQLException | JRException ex) {
            Util.addExceptionMessage(ex);
        } finally {
            response.getOutputStream().close();
        }
        facesContext.responseComplete();
    }

    public String imprimeBolsasAlmacen(Integer componenteId) throws Exception {
        Map<String, Object> pars = new HashMap<>();
        pars.put("componenteId", componenteId);
        printReportToBrowser(pars, "ImprimeBolsasAlmacen");
        return null;
    }

}
