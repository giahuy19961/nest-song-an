/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.dao.impl;

import com.mygroup.nestsonganver2.mapper.RoleMapper;
import com.mygroup.nestsonganver2.constant.RoleSQL;
import com.mygroup.nestsonganver2.dao.IRoleDAO;
import com.mygroup.nestsonganver2.entity.RoleEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dd220
 */
public class RoleDAO extends AbstractDAO<RoleEntity> implements IRoleDAO{
    
    public static RoleDAO instance;
    public static RoleDAO getRoleDAO() {
        if (instance == null) 
            instance = new RoleDAO();
        return instance;
    }
    
    private RoleDAO(){}

    //Get Role by id 
    @Override
    public RoleEntity getRoleById(int id) {
        List<RoleEntity> list = query(RoleSQL.findById, new RoleMapper(), id);
        if (list == null) return new RoleEntity();
        return list.get(0);
    }
    
    //Get all roles
    @Override
    public List<RoleEntity> getAllRole() {
        List<RoleEntity> list = query(RoleSQL.findAll, new RoleMapper());
        if (list == null) return new ArrayList<>();
        return list;
    }
}
