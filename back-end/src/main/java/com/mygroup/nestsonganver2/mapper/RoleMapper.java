/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.mapper;

import com.mygroup.nestsonganver2.entity.RoleEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dd220
 */
public class RoleMapper implements RowMapper<RoleEntity>{

//    public RoleMapper() {
//    }

    @Override
    public RoleEntity mapRow(ResultSet rs) {
        try {
            return new RoleEntity(
                    rs.getInt("id"),
                    rs.getString("name")
            );
        } catch (SQLException ex) {
            Logger.getLogger(RoleMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new RoleEntity();
    }
    
}
