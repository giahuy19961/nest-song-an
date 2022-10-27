/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygroup.nestsonganver2.mapper;


import com.mygroup.nestsonganver2.entity.UserEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author huy
 */
public class UserMapper implements RowMapper<UserEntity>{

    @Override
    public UserEntity mapRow(ResultSet rs) {       
        try {
            UserEntity user = new UserEntity();
            user.setId(rs.getInt("id"));
            user.setFullname(rs.getString("fullname"));
            user.setDateOfBirth(rs.getDate("dateOfBirth"));
            user.setAddress(rs.getString("address"));
            user.setPhoneNumber(rs.getString("phoneNumber"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
//            user.setEmployeeId(rs.getInt("employeeId"));
            user.setStatus(rs.getInt("status"));
            user.setRoleId(rs.getInt("roleId"));
            user.setToken(rs.getString("token"));
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
       
    }
    
}
