/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.mapper;

import com.mygroup.nestsonganver2.entity.CategoryEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class CategoryMapper implements RowMapper<CategoryEntity>{
    public static CategoryMapper instance;
    
    public static CategoryMapper getInstance(){
        if(instance == null){
            instance = new CategoryMapper();
        }
        return instance;
    }
    @Override
    public CategoryEntity mapRow(ResultSet rs) {       
        try {
            CategoryEntity category = new CategoryEntity();
            category.setId(rs.getInt("id"));
            category.setName(rs.getString("name"));
            return category;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryMapper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
       
    }
}
