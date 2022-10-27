/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygroup.nestsonganver2.mapper;

import com.mygroup.nestsonganver2.entity.PaymentStatusEntity;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author huy
 */
public class PaymentStatusMapper implements RowMapper<PaymentStatusEntity>{

    @Override
    public PaymentStatusEntity mapRow(ResultSet rs) {
        try {
            return new PaymentStatusEntity(
                                rs.getInt("id")
                               ,rs.getString("code")

            );
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return new PaymentStatusEntity();
    }
    
}
