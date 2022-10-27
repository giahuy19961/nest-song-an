/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygroup.nestsonganver2.service;

import com.mygroup.nestsonganver2.converter.PaymentStatusConverter;
import com.mygroup.nestsonganver2.dao.impl.PaymentStatusDAO;
import com.mygroup.nestsonganver2.dto.PaymentStatusDTO;
import com.mygroup.nestsonganver2.entity.PaymentStatusEntity;
import java.util.List;

/**
 *
 * @author huy
 */
public class PaymentStatusService {
    
    
     private static PaymentStatusDAO paymentStatusDAO = PaymentStatusDAO.getInstance();
     
     private static PaymentStatusConverter paymentStatusConverter = PaymentStatusConverter.getInstance();

    private static PaymentStatusService instance;
    
    public static PaymentStatusService getPaymentStatusInstance() {
        if (instance == null)
            instance = new PaymentStatusService();
        return instance;
    }
    
    public List<PaymentStatusDTO> findAll(){
        List<PaymentStatusDTO> list;
        List<PaymentStatusEntity> entityList = paymentStatusDAO.findAll();
        list = paymentStatusConverter.toDTO(entityList);
        return list;
    }
    
    public PaymentStatusDTO findOneById(int id){
        PaymentStatusEntity entity = paymentStatusDAO.findOneById(id);
        if(entity != null && entity.getId() != 0){
            return paymentStatusConverter.toDTO(entity);
        }
        return new PaymentStatusDTO();
    }
}
