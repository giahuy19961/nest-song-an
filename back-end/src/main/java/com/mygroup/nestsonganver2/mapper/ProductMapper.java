/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.mapper;

import com.mygroup.nestsonganver2.entity.ProductEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class ProductMapper implements RowMapper<ProductEntity>{
    
    public static ProductMapper instance;
    
    public static ProductMapper getInstance(){
        if(instance == null){
            instance = new ProductMapper();
        }
        return instance;
    }
    @Override
    public ProductEntity mapRow(ResultSet rs) {       
        try {
            ProductEntity product = new ProductEntity();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setQuantity(rs.getInt("quantity"));
            product.setDeal(rs.getFloat("deal"));
            product.setDescription(rs.getString("description"));
            product.setBasePrice(rs.getFloat("basePrice"));
            product.setCateId(rs.getInt("cateId"));
            product.setStatus(rs.getInt("status"));
            return product;
        } catch (SQLException ex) {
            Logger.getLogger(ProductMapper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
       
    }
}
