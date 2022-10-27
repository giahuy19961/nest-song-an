/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.mapper;

import com.mygroup.nestsonganver2.entity.BillDetailsEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Silver King
 */
public class BillDetailsMapper implements RowMapper<BillDetailsEntity>{

    @Override
    public BillDetailsEntity mapRow(ResultSet rs) {
        try {
            BillDetailsEntity billDetails = new BillDetailsEntity();
            billDetails.setId(rs.getInt("id"));
            billDetails.setPrice(rs.getFloat("price"));
            billDetails.setQuantity(rs.getInt("quantity"));
            billDetails.setProductId(rs.getInt("productId"));
            billDetails.setBillId(rs.getInt("billId"));
            return billDetails;
        }catch (SQLException ex) {
            Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
