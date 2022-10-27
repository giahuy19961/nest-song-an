/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.converter;

import com.mygroup.nestsonganver2.dto.NewsDTO;
import com.mygroup.nestsonganver2.entity.NewsEntity;

/**
 *
 * @author dd220
 */
public class NewsConverter {
    
    public static NewsDTO convertEntityToDTO(NewsEntity entity) {
        return new NewsDTO (
                            entity.getId()
                           ,entity.getDescription()
                           ,entity.getTitle()
                           ,entity.getShortDescription()
                           ,entity.getEmpId());
    }
    
    public static NewsEntity convertDTOToEnity(NewsDTO dto) {
        return new NewsEntity(
                             dto.getDescription()
                            ,dto.getTitle()
                            ,dto.getShortDescription()
                            ,dto.getEmpId()
        );
    }
}
