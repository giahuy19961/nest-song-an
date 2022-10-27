/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.converter;

import com.mygroup.nestsonganver2.dao.impl.ImageDAO;
import com.mygroup.nestsonganver2.dto.ImageDTO;
import com.mygroup.nestsonganver2.dto.ProductDTO;
import com.mygroup.nestsonganver2.entity.ImageEntity;
import com.mygroup.nestsonganver2.entity.ProductEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ProductConverter {
    // Convert Entitty to DTO
    
   private static ProductConverter productConverter;
    
    public static ProductConverter getInstance(){
        if(productConverter == null){
            productConverter = new ProductConverter(); 
        }
        return productConverter;
    }
    
    private static final ImageDAO imageDAO = ImageDAO.getImageDAO();
    
    public List<ProductDTO> convertEntitytoDTO(List<ProductEntity> entityList){
        List<ProductDTO> DTOList=new ArrayList<>();
            for (ProductEntity entity : entityList)
                DTOList.add(convertEntitytoDTO(entity));         
        return DTOList;
    }
    
    
    public ProductDTO convertEntitytoDTO(ProductEntity entity){
        ProductDTO dto = new ProductDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setQuantity(entity.getQuantity());
        dto.setDeal(entity.getDeal());
        dto.setDescription(entity.getDescription());
        dto.setBasePrice(entity.getBasePrice());
        dto.setCateId(entity.getCateId());
        dto.setStatus(entity.getStatus());
        dto.setListImages(convertImages(imageDAO.getImagesByProductId(entity.getId())));
        return dto;
    }   
    // -----------------------------------------------------------------------
    
    // Convert Entitty to DTO
    
    public static ProductEntity convertDTOtoEntity(ProductDTO dto){
        ProductEntity entity = new ProductEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setQuantity(dto.getQuantity());
        entity.setDeal(dto.getDeal());
        entity.setDescription(dto.getDescription());
        entity.setBasePrice(dto.getBasePrice());
        entity.setCateId(dto.getCateId());
        entity.setStatus(dto.getStatus());
        return entity;
    } 
    
    // -----------------------------------------------------------------------
    private List<ImageDTO> convertImages(List<ImageEntity> ImageEntityList){
        List<ImageDTO> listImageDTO = new ArrayList<>();
            for (ImageEntity entity : ImageEntityList)
                listImageDTO.add(ImageConverter.ConvertEntityToDTO(entity));
            return listImageDTO;                 
    }
    
}
