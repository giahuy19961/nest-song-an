/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.converter;

import com.mygroup.nestsonganver2.dao.impl.BillDetailsDAO;
import com.mygroup.nestsonganver2.dao.impl.PaymentStatusDAO;
import com.mygroup.nestsonganver2.dto.BillDTO;
import com.mygroup.nestsonganver2.entity.BillEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Silver King
 */
public class BillConverter {

    private static final BillDetailsDAO BILL_DETAILS_DAO = BillDetailsDAO.getInstance();
    private static final PaymentStatusDAO PAYMENT_STATUS_DAO = PaymentStatusDAO.getInstance();
    private static final BillDetailsConverter BILL_DETAILS_CONVERTER = BillDetailsConverter.getInstance();

    private static BillConverter billConverter = null;

    public static BillConverter getInstance() {
        if (billConverter == null) {
            billConverter = new BillConverter();
        }
        return billConverter;
    }

    public BillDTO convertEntitytoDTO(BillEntity entity) {
        BillDTO dto = new BillDTO();
        dto.setId(entity.getId());
        dto.setDate(entity.getDate());
        dto.setStatus(entity.getStatus());
        dto.setCustomerId(entity.getCustomerId());
        dto.setEmpId(entity.getEmpId());
        dto.setListBillDetails(BILL_DETAILS_CONVERTER.convertListEntitytoDTO(BILL_DETAILS_DAO.findByBillId(entity.getId())));
        dto.setTotalPrice(entity.getTotalPrice());
        dto.setAddress(entity.getAddress());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setPaymentStatusCode(PAYMENT_STATUS_DAO.findOneById(entity.getPaymentStatusId()).getCode());
        return dto;
    }

    public BillEntity convertDTOtoEntity(BillDTO dto) {
        BillEntity entity = new BillEntity();
        entity.setId(dto.getId());
        entity.setDate(dto.getDate());
        entity.setStatus(dto.getStatus());
        entity.setCustomerId(dto.getCustomerId());
        entity.setEmpId(dto.getEmpId());
        entity.setTotalPrice(dto.getTotalPrice());
        entity.setAddress(dto.getAddress());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setPaymentStatusId(dto.getPaymentStatusCodeId());
        return entity;
    }

    public List<BillDTO> convertListEntitytoDTO(List<BillEntity> list) {
        List<BillDTO> result = new ArrayList<>();
        for (BillEntity billEntity : list) {
            BillDTO dto = billConverter.convertEntitytoDTO(billEntity);
            result.add(dto);
        }
        return result;
    }

    public List<BillEntity> convertListDTOtoEntity(List<BillDTO> list) {
        List<BillEntity> result = new ArrayList<>();
        for (BillDTO billDTO : list) {
            result.add(billConverter.convertDTOtoEntity(billDTO));
        }
        return result;
    }
}
