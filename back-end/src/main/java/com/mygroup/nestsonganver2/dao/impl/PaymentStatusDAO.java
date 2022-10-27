/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygroup.nestsonganver2.dao.impl;

import com.mygroup.nestsonganver2.constant.PaymentStatusSQL;
import com.mygroup.nestsonganver2.dao.IPaymentStatusDAO;
import com.mygroup.nestsonganver2.entity.PaymentStatusEntity;
import com.mygroup.nestsonganver2.mapper.PaymentStatusMapper;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huy
 */
public class PaymentStatusDAO extends AbstractDAO<PaymentStatusEntity> implements IPaymentStatusDAO{
    
    private static PaymentStatusDAO paymentStatusDao = null;

    public static PaymentStatusDAO getInstance() {
        if (paymentStatusDao == null) {
            paymentStatusDao = new PaymentStatusDAO();
        }
        return paymentStatusDao;
    }

    @Override
    public List<PaymentStatusEntity> findAll() {
        List<PaymentStatusEntity> entityList = query(PaymentStatusSQL.findAll, new PaymentStatusMapper());
        if(entityList == null) return new ArrayList<>();
        return entityList;
    }

    @Override
    public PaymentStatusEntity findOneById(int id) {
        List<PaymentStatusEntity> entityList = query(PaymentStatusSQL.findOneById, new PaymentStatusMapper(), id);
        if(entityList == null) return null;
        return entityList.isEmpty() ? null : entityList.get(0);
    }
    
}
