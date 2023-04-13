/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hemoser.bdspr.service;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Sergio Morales
 */
@ManagedBean(name = "UtilService", eager = true)
@ApplicationScoped
public class UtilService implements Serializable {

    public String encodePar(String par) throws UnsupportedEncodingException {
        String originalInput = par;
        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
        return URLEncoder.encode(encodedString, StandardCharsets.UTF_8.name());
    }

    public String encodeIntegerPar(Integer par) throws UnsupportedEncodingException {
        if (par != null) {
            return encodePar(par.toString());
        } else {
            return null;
        }
    }

    public String ConvertLocalDateTime(LocalDateTime value) {
        if (value == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return value.format(formatter);
    }

    public String ConvertLocalDate(LocalDate value) {
        if (value == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return value.format(formatter);
    }
}
