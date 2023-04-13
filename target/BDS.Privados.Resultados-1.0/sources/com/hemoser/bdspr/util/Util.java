/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hemoser.bdspr.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.time.ZoneId;
import java.util.Base64;
import java.util.HashMap;
import javax.servlet.http.Cookie;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.primefaces.PrimeFaces;
import org.primefaces.context.PrimeFacesContext;

/**
 *
 * @author max
 */
public class Util {

    public static void addInfoMessage(String summary, String detalle) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detalle);
        FacesContext.getCurrentInstance().addMessage(null, message);
        PrimeFaces.current().ajax().addCallbackParam("error", false);
    }

    public static void addInfoMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "", summary);
        FacesContext.getCurrentInstance().addMessage(null, message);
        PrimeFaces.current().ajax().addCallbackParam("error", false);
    }

    public static void addWarnMessage(String summary, String detalle) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, summary, detalle);
        FacesContext.getCurrentInstance().addMessage(null, message);
        PrimeFaces.current().ajax().addCallbackParam("error", false);
    }

    public static void addWarnMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "", summary);
        FacesContext.getCurrentInstance().addMessage(null, message);
        PrimeFaces.current().ajax().addCallbackParam("error", false);
    }

    public static void addErrMessage(String summary, String detalle) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detalle);
        FacesContext.getCurrentInstance().addMessage(null, message);
        PrimeFaces.current().ajax().addCallbackParam("error", true);
    }

    public static void addErrMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", summary);
        FacesContext.getCurrentInstance().addMessage(null, message);
        PrimeFaces.current().ajax().addCallbackParam("error", true);
    }

    public static void addExceptionMessage(Exception ex) {
        String detalleExcepcion = ex.getMessage();
        if (detalleExcepcion == null || detalleExcepcion.isEmpty()) {
            detalleExcepcion = ex.toString();
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Excepción", detalleExcepcion);
        FacesContext.getCurrentInstance().addMessage(null, message);
        PrimeFaces.current().ajax().addCallbackParam("error", true);
    }

    public static String getParam(String key) {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        return params.get(key);
    }

    public static Integer getUserId() {
        return Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userid").toString());
    }

    public static void setUserId(Integer userid) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userid", userid);
    }

    public static void redirect(String url) {
        PrimeFacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, url + "?faces-redirect=true");
    }

    public static Date getCurrentDate() {
        LocalDate date = LocalDate.now();
        return java.util.Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static void setDateParameter(CallableStatement cs, String parameterName, Date dateValue) throws SQLException {
        if (dateValue != null) {
            cs.setDate(parameterName, new java.sql.Date(dateValue.getTime()));
        } else {
            cs.setDate(parameterName, null);
        }
    }

    public static void setLocalDateParameter(CallableStatement cs, String parameterName, LocalDate dateValue) throws SQLException {
        if (dateValue != null) {
            Timestamp timestamp = Timestamp.valueOf(dateValue.atStartOfDay());
            cs.setTimestamp(parameterName, timestamp);
        } else {
            cs.setNull(parameterName, Types.TIMESTAMP);
        }
    }

    public static void setDateParameter(PreparedStatement ps, Integer parameter, Date dateValue) throws SQLException {
        if (dateValue != null) {
            ps.setDate(parameter, new java.sql.Date(dateValue.getTime()));
        } else {
            ps.setDate(parameter, null);
        }
    }

    public static void setDateTimeParameter(CallableStatement cs, String parameterName, Date dateValue) throws SQLException {
        if (dateValue != null) {
            cs.setTimestamp(parameterName, new java.sql.Timestamp(dateValue.getTime()));
        } else {
            cs.setTimestamp(parameterName, null);
        }
    }

    public static void setDateTimeParameter(CallableStatement cs, String parameterName, LocalDateTime dateValue) throws SQLException {
        if (dateValue != null) {
            cs.setTimestamp(parameterName, Timestamp.valueOf(dateValue));
        } else {
            cs.setTimestamp(parameterName, null);
        }
    }

    public static void setOnlyTimeParameter(CallableStatement cs, String parameterName, Date dateValue) throws SQLException {
        if (dateValue != null) {
            //Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("hh:mm");
            String strDate = dateFormat.format(dateValue);

            cs.setString(parameterName, strDate);
        } else {
            cs.setString(parameterName, null);
        }
    }

    public static void setIntegerParameter(CallableStatement cs, String parameterName, Integer integerValue) throws SQLException {
        if (integerValue != null) {
            cs.setInt(parameterName, integerValue);
        } else {
            cs.setNull(parameterName, Types.INTEGER);
        }
    }

    public static void setIntegerParameter(CallableStatement cs, int parameterName, Integer integerValue) throws SQLException {
        if (integerValue != null) {
            cs.setInt(parameterName, integerValue);
        } else {
            cs.setNull(parameterName, Types.INTEGER);
        }
    }

    public static void setIntegerParameter(PreparedStatement ps, int parameterName, Integer integerValue) throws SQLException {
        if (integerValue != null) {
            ps.setInt(parameterName, integerValue);
        } else {
            ps.setNull(parameterName, Types.INTEGER);
        }
    }

    public static void setBigDecimalParemeter(CallableStatement cs, String parameterName, BigDecimal bigdecimalValue) throws SQLException {
        if (bigdecimalValue != null) {
            cs.setBigDecimal(parameterName, bigdecimalValue);
        } else {
            cs.setNull(parameterName, Types.DECIMAL);
        }
    }

    public static void setBoolParameter(CallableStatement cs, String parameterName, Boolean boolValue) throws SQLException {
        if (boolValue != null) {
            cs.setBoolean(parameterName, boolValue);
        } else {
            cs.setBoolean(parameterName, false);
        }
    }

    public static void setBoolParameter(CallableStatement cs, int parameterName, Boolean boolValue) throws SQLException {
        if (boolValue != null) {
            cs.setBoolean(parameterName, boolValue);
        } else {
            cs.setNull(parameterName, Types.BOOLEAN);
        }
    }

    public static void setBoolParameter(PreparedStatement ps, int parameterName, Boolean boolValue) throws SQLException {
        if (boolValue != null) {
            ps.setBoolean(parameterName, boolValue);
        } else {
            ps.setNull(parameterName, Types.BOOLEAN);
        }
    }

    public static void setFloatParameter(CallableStatement cs, String parameterName, Float floatValue) throws SQLException {
        if (floatValue != null) {
            cs.setFloat(parameterName, floatValue);
        } else {
            cs.setNull(parameterName, Types.FLOAT);
        }
    }

    public static void setStringLike(CallableStatement cs, String parameterName, String value) throws SQLException {
        if (value != null && !value.trim().isEmpty()) {
            cs.setString(parameterName, "%" + value + "%");
        } else {
            cs.setNull(parameterName, Types.VARCHAR);
        }
    }

    public static void setStringEqual(CallableStatement cs, String parameterName, String value) throws SQLException {
        if (value != null && !value.trim().isEmpty()) {
            cs.setString(parameterName, value);
        } else {
            cs.setNull(parameterName, Types.VARCHAR);
        }
    }

    public static void setStringEqual(PreparedStatement cs, int parameterNumber, String value) throws SQLException {
        if (value != null && !value.trim().isEmpty()) {
            cs.setString(parameterNumber, value);
        } else {
            cs.setNull(parameterNumber, Types.VARCHAR);
        }
    }

    public static void setStringParameter(PreparedStatement ps, int parameterName, String stringValue) throws SQLException {
        if (stringValue != null) {
            ps.setString(parameterName, stringValue);
        } else {
            ps.setNull(parameterName, Types.VARCHAR);
        }
    }

    public static void setStringParameter(CallableStatement cs, int parameterName, String stringValue) throws SQLException {
        if (stringValue != null) {
            cs.setString(parameterName, stringValue);
        } else {
            cs.setNull(parameterName, Types.VARCHAR);
        }
    }

    public static void setStringParameterNotNull(CallableStatement cs, int parameterName, String stringValue) throws SQLException {
        if (stringValue != null) {
            cs.setString(parameterName, stringValue);
        } else {
            cs.setString(parameterName, "");
        }
    }

    public static void setStringParameterNotNull(CallableStatement cs, String parameterName, String stringValue) throws SQLException {
        if (stringValue != null) {
            cs.setString(parameterName, stringValue);
        } else {
            cs.setString(parameterName, "");
        }
    }

    public static Integer tryParseInteger(String value) {
        if (value == null) {
            return null;
        }

        if (value.isEmpty()) {
            return null;
        }

        try {
            return Integer.parseInt(value);
        } catch (Exception ex) {
            return null;
        }
    }

    public static Date tryParseDate(String value) {
        if (value == null) {
            return null;
        }

        if (value.isEmpty()) {
            return null;
        }

        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(value);
        } catch (Exception ex) {
            return null;
        }
    }

    public static String setStringAsEmpty(String value) {
        if (value == null) {
            return "";
        } else {
            return value;
        }

    }

    public static String getBase64Param(String key) throws Exception {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String par = params.get(key);
        if (par == null) {
            return "";
        }
        par = URLDecoder.decode(par, StandardCharsets.UTF_8.name());
        byte[] decodedBytes = Base64.getDecoder().decode(par);
        String decodedString = new String(decodedBytes);
        return decodedString;
    }

    public static String decodePar(String par) throws UnsupportedEncodingException {
        par = URLDecoder.decode(par, StandardCharsets.UTF_8.name());
        byte[] decodedBytes = Base64.getDecoder().decode(par);
        String decodedString = new String(decodedBytes);
        return decodedString;
    }

    public static String encodePar(String par) throws UnsupportedEncodingException {
        if (par != null) {
            String originalInput = par;
            String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
            return URLEncoder.encode(encodedString, StandardCharsets.UTF_8.name());
        } else {
            return null;
        }
    }

    public static String encodeIntegerPar(Integer par) throws UnsupportedEncodingException {
        if (par != null) {
            return encodePar(par.toString());
        } else {
            return null;
        }
    }

    public static String getCookieValue(String key) {
        Cookie cookie = (Cookie) FacesContext.getCurrentInstance().getExternalContext().getRequestCookieMap().get(key);
        if (cookie == null || cookie.getValue().equals("")) {
            return null;
        } else {
            return cookie.getValue();
        }
    }

    public static void setCookieValue(String key, Object value) {
        String strValue;
        if (value == null) {
            strValue = null;
        } else if (value instanceof Date) {
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            strValue = dateFormat.format(value);
        } else if (value instanceof Integer) {
            strValue = value.toString();
        } else if (value instanceof Long) {
            strValue = value.toString();
        } else if (value instanceof Double) {
            strValue = value.toString();
        } else if (value instanceof Float) {
            strValue = value.toString();
        } else if (value instanceof BigDecimal) {
            strValue = value.toString();
        } else if (value instanceof Boolean) {
            strValue = value.toString();
        } else {
            strValue = (String) value;
        }
        Map<String, Object> properties = new HashMap<>();
        properties.put("maxAge", -1);
        FacesContext.getCurrentInstance().getExternalContext().addResponseCookie(key, strValue, properties);
    }

    public static void setTimedCookieValue(String key, Object value, int expirationMinutes) {
        String strValue;
        if (value == null) {
            strValue = null;
        } else if (value instanceof Date) {
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            strValue = dateFormat.format(value);
        } else if (value instanceof Integer) {
            strValue = value.toString();
        } else if (value instanceof Long) {
            strValue = value.toString();
        } else if (value instanceof Double) {
            strValue = value.toString();
        } else if (value instanceof Float) {
            strValue = value.toString();
        } else if (value instanceof BigDecimal) {
            strValue = value.toString();
        } else if (value instanceof Boolean) {
            strValue = value.toString();
        } else {
            strValue = (String) value;
        }
        Map<String, Object> properties = new HashMap<>();
        properties.put("maxAge", (expirationMinutes * 60));
        FacesContext.getCurrentInstance().getExternalContext().addResponseCookie(key, strValue, properties);
    }

    public static void deleteCookie(String key) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("maxAge", 0);
        FacesContext.getCurrentInstance().getExternalContext().addResponseCookie(key, "", properties);
    }

    public static void sendEmail(String sendToEmail, String subject, String message) throws EmailException {
        String hostUsername = "email@provider"; //correo
        String hostPassword = "password"; //contraseña correo
        Email email = new SimpleEmail();
        email.setHostName("smtp.gmail.com"); //nombre host smtp del correo
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(hostUsername, hostPassword));
        email.setSSLOnConnect(true);
        email.setFrom(""); //de quién se manda, usualmente el mismo nombre que el correo
        email.setSubject(subject);
        email.setMsg(message);
        email.addTo(sendToEmail);
        email.send();
    }

    public static void executeScript(String value) {
        PrimeFaces.current().executeScript(value);
    }
}
