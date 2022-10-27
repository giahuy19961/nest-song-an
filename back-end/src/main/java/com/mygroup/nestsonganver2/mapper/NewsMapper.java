/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.mapper;

import com.mygroup.nestsonganver2.entity.NewsEntity;
import java.sql.ResultSet;

/**
 *
 * @author dd220
 */
public class NewsMapper implements RowMapper<NewsEntity>{

    @Override
    public NewsEntity mapRow(ResultSet rs) {
        try {
            return new NewsEntity(
                                rs.getInt("id")
                               ,rs.getString("description")
                               ,rs.getString("title")
                               ,rs.getString("shortDescription")
                               ,rs.getInt("empId")
            );
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return new NewsEntity();
    }
    
}
