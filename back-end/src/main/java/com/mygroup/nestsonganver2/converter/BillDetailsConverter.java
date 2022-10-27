/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.converter;

import com.mygroup.nestsonganver2.dao.impl.ProductDAO;
import com.mygroup.nestsonganver2.dto.BillDetailsDTO;
import com.mygroup.nestsonganver2.dto.ProductDTO;
import com.mygroup.nestsonganver2.entity.BillDetailsEntity;
import com.mygroup.nestsonganver2.entity.ProductEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Silver King
 */
public class BillDetailsConverter {

    private static ProductDAO productDAO = ProductDAO.getInstance();
    private static ProductConverter productConverter = ProductConverter.getInstance();
    
    private static BillDetailsConverter billDetailsConverter = null;
    
    public static BillDetailsConverter getInstance(){
        if (billDetailsConverter==null){
            billDetailsConverter = new BillDetailsConverter();
        }
        return billDetailsConverter;
    }
    
    public BillDetailsDTO convertEntitytoDTO(BillDetailsEntity entity) {
        BillDetailsDTO dto = new BillDetailsDTO();
        dto.setId(entity.getId());
        dto.setPrice(entity.getPrice());
        dto.setQuantity(entity.getQuantity());
        ProductEntity product = productDAO.getProductById(entity.getProductId());
        if (product == null) {
            dto.setProduct(new ProductDTO());
        } else {
            dto.setProduct(productConverter.convertEntitytoDTO(product));
        }
        dto.setBillId(entity.getBillId());
        return dto;
    }

    public  BillDetailsEntity convertDTOtoEntity(BillDetailsDTO dto) {
        BillDetailsEntity entity = new BillDetailsEntity();
        entity.setId(dto.getId());
        entity.setPrice(dto.getProduct().getBasePrice()*(1-dto.getProduct().getDeal()));
        entity.setQuantity(dto.getQuantity());
        entity.setProductId(dto.getProduct().getId());
        entity.setBillId(dto.getBillId());
        return entity;
    }

    public List<BillDetailsDTO> convertListEntitytoDTO(List<BillDetailsEntity> list) {
        List<BillDetailsDTO> result = new ArrayList<>();
        list.forEach(billDetailsEntity -> {
            result.add(billDetailsConverter.convertEntitytoDTO(billDetailsEntity));
        });
        return result;
    }

    public List<BillDetailsEntity> convertListDTOtoEntity(List<BillDetailsDTO> list) {
        List<BillDetailsEntity> result = new ArrayList<>();
        list.forEach(billDetailsDTO -> {
            result.add(billDetailsConverter.convertDTOtoEntity(billDetailsDTO));
        });
        return result;
    }
}
