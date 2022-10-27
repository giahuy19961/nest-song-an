/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.converter;

import com.mygroup.nestsonganver2.dto.ImageDTO;
import com.mygroup.nestsonganver2.entity.ImageEntity;

/**
 *
 * @author dd220
 */
public class ImageConverter {
    public static ImageDTO ConvertEntityToDTO(ImageEntity entity ) {
        return new ImageDTO(
                entity.getId(),
                entity.getImgPath(),
                entity.getProductId(),
                entity.getNewsId()
        );
    }
    
    public static ImageEntity ConvertDTOToEntity(ImageDTO dto) {
        if (dto.getNewsId() == 0 && dto.getProductId() != 0)
            return new ImageEntity(
                    dto.getId(),
                    dto.getImgPath(),
                    dto.getProductId(),
                    null
            );
        if (dto.getNewsId() != 0 && dto.getProductId() == 0)
            return new ImageEntity(
                    dto.getId(),
                    dto.getImgPath(),
                    null,
                    dto.getNewsId()
            );
        
        if (dto.getNewsId() == 0 && dto.getProductId() == 0)
            return new ImageEntity(
                    dto.getId(),
                    dto.getImgPath(),
                    null,
                    null
            );
        return new ImageEntity(
                dto.getId(),
                dto.getImgPath(),
                dto.getProductId(),
                dto.getNewsId()
        );
    }

}
