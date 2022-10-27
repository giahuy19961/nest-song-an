/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.converter;

import com.mygroup.nestsonganver2.dto.CategoryDTO;
import com.mygroup.nestsonganver2.entity.CategoryEntity;

/**
 *
 * @author ADMIN
 */
public class CategoryConverter {
    public static CategoryDTO convertEntitytoDTO(CategoryEntity entity){
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }   
    // -----------------------------------------------------------------------
    
    // Convert Entitty to DTO
    
    public static CategoryEntity convertDTOtoEntity(CategoryDTO dto){
        CategoryEntity entity = new CategoryEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());   
        return entity;
    } 
    
    
}
