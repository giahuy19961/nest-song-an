/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.mapper;

import com.mygroup.nestsonganver2.entity.ImageEntity;
import java.sql.ResultSet;

/**
 *
 * @author dd220
 */
public class ImageMapper implements RowMapper{

    @Override
    public ImageEntity mapRow(ResultSet rs) {
        ImageEntity entity = new ImageEntity();
        try{
            entity.setId(rs.getInt("id"));
            entity.setImgPath(rs.getString("path"));
            entity.setNewsId(rs.getInt("newsId"));
            entity.setProductId(rs.getInt("productId"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return entity;
    }
    
}
