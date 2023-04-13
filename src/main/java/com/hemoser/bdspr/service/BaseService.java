/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hemoser.bdspr.service;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Sergio
 */
public class BaseService implements Serializable {

    protected static DataSource GetDataSource() {
        try {
            Context ctx = new InitialContext();
            return (DataSource) ctx.lookup("java:comp/env/jdbc/bdspDS");
        } catch (NamingException ex) {
            Logger.getLogger(BaseService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
