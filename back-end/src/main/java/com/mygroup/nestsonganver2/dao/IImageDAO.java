/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.dao;

import com.mygroup.nestsonganver2.entity.ImageEntity;
import java.util.List;

/**
 *
 * @author dd220
 */
public interface IImageDAO extends IDao<ImageEntity>{
    
    // Add Image
    public int addImage(ImageEntity imgEntity);
    
    //----------------------------------------------------------------------------------
    
    //Get Image 
    public List<ImageEntity> getAllImages();
    
    //Get image by id
    public ImageEntity getImage(int id);
    
    //Get Id of last image
    public int getIdOfLastImage();
    
    public List<ImageEntity> getImagesByProductId(int productId); 
    
    public List<ImageEntity> getImageByNewsId(int newsId);
    //----------------------------------------------------------------------------------
    
    //Update Image
    public int updateImageById(ImageEntity imgEntity);
    //----------------------------------------------------------------------------------
    
    //Delete Image
    public boolean deleteImage(int id);
}
