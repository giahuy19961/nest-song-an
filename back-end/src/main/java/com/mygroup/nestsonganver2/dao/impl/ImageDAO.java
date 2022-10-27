/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.dao.impl;

import com.mygroup.nestsonganver2.constant.ImageSQL;
import com.mygroup.nestsonganver2.dao.IImageDAO;
import com.mygroup.nestsonganver2.entity.ImageEntity;
import com.mygroup.nestsonganver2.mapper.ImageMapper;
import com.mygroup.nestsonganver2.utils.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dd220
 */
public class ImageDAO extends AbstractDAO<ImageEntity> implements IImageDAO {
    
    
    //Singleton pattern
    private static ImageDAO instance; 
    
    public static ImageDAO getImageDAO () {
        if (instance == null)
            instance = new ImageDAO();
        return instance;
    }

    private ImageDAO(){}
    
    
    //Get Image
    @Override
    public List<ImageEntity> getAllImages() {
        List<ImageEntity> list = query(ImageSQL.findAll, new ImageMapper());
        if (list == null) return new ArrayList<>();
        return list;
    }
    
    @Override
    public ImageEntity getImage(int id) {
        List<ImageEntity> img = query(ImageSQL.findById, new ImageMapper(), id);
        if (img == null || img.isEmpty())
            return null;
        return img.get(0);
        
    }
    
    @Override
    public int getIdOfLastImage(){
//        String sql1 = "SELECT TOP 1 id FROM Images ORDER BY id DESC";
//        List<ImageEntity> imgs= query(sql1, new ImageMapper());
        Connection cnn = null;
        int id = 0;
        try {
            cnn = Utils.makeConnection();
            if (cnn != null){
                String sql = "SELECT TOP 1 id FROM Images ORDER BY id DESC";
                Statement st = cnn.createStatement();
                ResultSet rs = st.executeQuery(sql); 
                if (rs.next()) {
                    id = rs.getInt("id");
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return id;
    }
    
    @Override
    public List<ImageEntity> getImagesByProductId(int productId) { 
        List<ImageEntity> list = query(ImageSQL.findByProductId, new ImageMapper(), productId);
        return list;
    }
    
    @Override
    public List<ImageEntity> getImageByNewsId(int newsId) {
        List<ImageEntity> list = query(ImageSQL.findByNewsId, new ImageMapper(), newsId);
        return list;
    }
    
    //Add new Image
    @Override
    public int addImage(ImageEntity imgEntity) {
        Connection cnn = null;
        int result = 0;
        try {
            cnn = Utils.makeConnection();
            if (cnn != null) {
                String sql ="";
                if (imgEntity.getNewsId() != null && imgEntity.getProductId() == null) {
                    sql = "INSERT Images (path, newsId) values (?, ?)";
                    PreparedStatement ps = cnn.prepareStatement(sql);
                    ps.setString(1, imgEntity.getImgPath());
                    ps.setInt(2, imgEntity.getNewsId().intValue());
                    result = ps.executeUpdate();
                } else if (imgEntity.getNewsId() == null && imgEntity.getProductId() != null) {
                    sql = "INSERT Images (path, productId) values (?, ?)";
                    PreparedStatement ps = cnn.prepareStatement(sql);
                    ps.setString(1, imgEntity.getImgPath());
                    ps.setInt(2, imgEntity.getProductId().intValue());
                    result = ps.executeUpdate();
                } else if (imgEntity.getNewsId() != null && imgEntity.getProductId() != null) {
                    sql = "INSERT Images (path, productId, newsId) values (?, ?, ?)";
                    PreparedStatement ps = cnn.prepareStatement(sql);
                    ps.setString(1, imgEntity.getImgPath());
                    ps.setInt(2, imgEntity.getProductId().intValue());
                    ps.setInt(3, imgEntity.getNewsId().intValue());
                    result = ps.executeUpdate();
                } else {
                    sql = "INSERT Images (path) values (?)";
                    PreparedStatement ps = cnn.prepareStatement(sql);
                    ps.setString(1, imgEntity.getImgPath());
                    result = ps.executeUpdate();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } 
        return result;
    }

    
    //Update Image
    @Override
    public int updateImageById(ImageEntity imgEntity) {
        int result = update(ImageSQL.updateImageById, imgEntity.getImgPath(), imgEntity.getNewsId(), imgEntity.getProductId(), imgEntity.getProductId());
        return result;
    }

    @Override
    public boolean deleteImage(int id) {
        boolean check = false;
        Connection cnn = null;
        try {
           cnn = Utils.makeConnection();
           if (cnn != null) {
               String sql = "DELETE Images WHERE id = (?)";
               PreparedStatement ps = cnn.prepareStatement(sql);
               ps.setInt(1, id);
               ps.execute();
               check = true;
           }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return check;
    }

    

    
    
}
