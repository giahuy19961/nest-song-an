/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.dao;

import com.mygroup.nestsonganver2.entity.RoleEntity;
import java.util.List;

/**
 *
 * @author dd220
 */
public interface IRoleDAO extends IDao<RoleEntity>{
    
    //GET Role By ID
    public RoleEntity getRoleById(int id);
    
    //Get all Roles
    public List<RoleEntity> getAllRole();
}
