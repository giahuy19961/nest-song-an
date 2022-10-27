/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygroup.nestsonganver2.converter;

import com.mygroup.nestsonganver2.dto.PaymentStatusDTO;
import com.mygroup.nestsonganver2.entity.PaymentStatusEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huy
 */
public class PaymentStatusConverter {
    
    
    private static PaymentStatusConverter paymentStatusConverter;
    
    public static PaymentStatusConverter getInstance(){
        if(paymentStatusConverter == null){
            paymentStatusConverter = new PaymentStatusConverter(); 
        }
        return paymentStatusConverter;
    }
    
    
    public PaymentStatusDTO toDTO(PaymentStatusEntity entity) {
        PaymentStatusDTO dto = new PaymentStatusDTO();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        return dto;
    }
    
    public List<PaymentStatusDTO> toDTO(List<PaymentStatusEntity> entityList) {
        List<PaymentStatusDTO> dtoList = new ArrayList<>();      
        entityList.forEach(entity -> dtoList.add(toDTO(entity)));
        return dtoList;
    }
    
    public PaymentStatusEntity toEntity(PaymentStatusDTO dto) {
        PaymentStatusEntity entity = new PaymentStatusEntity();
        entity.setId(dto.getId());
        entity.setCode(dto.getCode());
        return entity;
    }
    
    public List<PaymentStatusEntity> toEntity(List<PaymentStatusDTO> dtoList) {
        List<PaymentStatusEntity> entityList = new ArrayList<>();      
        dtoList.forEach(dto -> entityList.add(toEntity(dto)));
        return entityList;
    }
}
