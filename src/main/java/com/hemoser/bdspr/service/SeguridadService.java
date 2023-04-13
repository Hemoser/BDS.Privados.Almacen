/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hemoser.bdspr.service;

import com.hemoser.bdspr.model.PerfilMenuM;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Sergio Morales
 */
@ManagedBean(name = "SeguridadService")
@SessionScoped
public class SeguridadService {

    private List<PerfilMenuM> items = new ArrayList();

    @PostConstruct
    public void init() {
        items = GeneralService.selectPerfilMenu();
    }

    public boolean menuIsActive(String menuId) {
        PerfilMenuM item = items.stream().filter(i -> i.getMenuid().equals(menuId)).findAny().orElse(null);
        if (item == null) {
            return false;
        } else {
            return item.isActivo();
        }
    }
}
