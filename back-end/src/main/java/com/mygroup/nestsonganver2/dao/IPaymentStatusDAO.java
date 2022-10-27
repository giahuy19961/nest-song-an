/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygroup.nestsonganver2.dao;

import com.mygroup.nestsonganver2.entity.PaymentStatusEntity;
import java.util.List;

/**
 *
 * @author huy
 */
public interface IPaymentStatusDAO extends IDao<PaymentStatusEntity>{
    
    
    public List<PaymentStatusEntity> findAll();
    
    public PaymentStatusEntity findOneById(int id);
}
