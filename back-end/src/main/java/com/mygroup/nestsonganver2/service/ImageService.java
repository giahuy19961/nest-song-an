/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.service;

import com.mygroup.nestsonganver2.converter.ImageConverter;
import com.mygroup.nestsonganver2.dao.impl.ImageDAO;
import com.mygroup.nestsonganver2.dto.ImageDTO;
import com.mygroup.nestsonganver2.entity.ImageEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author dd220
 */
public class ImageService {
    
    private ImageDAO imgDAO = ImageDAO.getImageDAO();

    public static ImageService instance;
    
    public static ImageService getImageService() {
        if (instance == null)
            instance = new ImageService();
        return instance;
    }
    
    private ImageService(){}
    
    //Add new image
    public int addImage(ImageDTO img){
            imgDAO.addImage(ImageConverter.ConvertDTOToEntity(img));
            return imgDAO.getIdOfLastImage();
    }
//-------------------------------------------------------------------------------------------------------------------------------
    //Get Image
    public List<ImageDTO> getAllImages() {
        List<ImageEntity> list = imgDAO.getAllImages();
        if (list == null) return new ArrayList<>();
        ImageDTO dto;
        List<ImageDTO> listDTO = new ArrayList<>();
        for (ImageEntity entity: list) {
            dto = ImageConverter.ConvertEntityToDTO(entity);
            listDTO.add(dto);
        }
        return listDTO;
    }
    
    
    public ImageDTO getImageById(int id) {
        ImageEntity entity = imgDAO.getImage(id);
        if (entity == null) return null;
        ImageDTO dto = ImageConverter.ConvertEntityToDTO(entity);
        return dto;
    }
    
    public List<ImageDTO> getImagesByProductId(int productId) {
        List<ImageEntity> entityList = imgDAO.getImagesByProductId(productId);
//        if (entityList.isEmpty() || entityList == null) return null;
        ImageDTO dto;
        List<ImageDTO> listDTO = new ArrayList<>();
        for(ImageEntity entity : entityList) {
            dto = ImageConverter.ConvertEntityToDTO(entity);
            listDTO.add(dto);
        }
        return listDTO;
    }
    
    public List<ImageDTO> getImagesByNewsId(int newsId) {
        List<ImageEntity> entityList = imgDAO.getImageByNewsId(newsId);
        if (entityList == null || entityList.isEmpty()) return new ArrayList<>();
        ImageDTO dto;
        List<ImageDTO> listDTO = new ArrayList<>();
        for(ImageEntity entity : entityList) {
            dto = ImageConverter.ConvertEntityToDTO(entity);
            listDTO.add(dto);
        }
        return listDTO;
    }
//-------------------------------------------------------------------------------------------------------------------------------
    //Update image by ID
    public ImageDTO updateImageById(int id, ImageDTO dto) {
        ImageDTO old = getImageById(id);
        
        if (old == null) return null;
        if (dto == null) return old;
        
        if (dto.getImgPath() != null) old.setImgPath(dto.getImgPath());
        if (dto.getNewsId() != null && dto.getNewsId() != 0) old.setNewsId(dto.getNewsId());
        if (dto.getProductId() != null && dto.getProductId() != 0) old.setProductId(dto.getProductId());
        
        imgDAO.updateImageById(ImageConverter.ConvertDTOToEntity(old)); 
        return old;
    }
//-------------------------------------------------------------------------------------------------------------------------------

    //Delete image by ID
    public boolean deleteImage(int id) {
        if (imgDAO.deleteImage(id))
            return true;
        return false;
    }

    

    
}
