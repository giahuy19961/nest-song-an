/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.dao.impl;

import com.mygroup.nestsonganver2.constant.BillDetailsSQL;
import com.mygroup.nestsonganver2.dao.IBillDetailsDAO;
import com.mygroup.nestsonganver2.entity.BillDetailsEntity;
import com.mygroup.nestsonganver2.entity.BillEntity;
import com.mygroup.nestsonganver2.mapper.BillDetailsMapper;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Silver King
 */
public class BillDetailsDAO extends AbstractDAO<BillDetailsEntity> implements IBillDetailsDAO {

    private static BillDetailsDAO billDetailsDAO = null;

    public static BillDetailsDAO getInstance() {
        if (billDetailsDAO == null) {
            billDetailsDAO = new BillDetailsDAO();
        }
        return billDetailsDAO;
    }

    //create new
    @Override
    public int insertNewBillDetails(BillDetailsEntity billDetails) {
        int id = insert(BillDetailsSQL.insertNew, billDetails.getPrice(), billDetails.getQuantity(), billDetails.getProductId(), billDetails.getBillId());
        return id;
    }

    public int insertNewListBillDetails(List<BillDetailsEntity> billDetails, int billID) {
        int checkResult = 0;
        for (BillDetailsEntity billDetail : billDetails) {
            billDetail.setBillId(billID);
            checkResult = insertNewBillDetails(billDetail);
            if (checkResult == 0) {
                return 0;
            }
        }
        return checkResult;
    }

    //find bill
    @Override
    public List<BillDetailsEntity> findAll() {
        List<BillDetailsEntity> list = new ArrayList<>();
        list = query(BillDetailsSQL.findAll, new BillDetailsMapper());
        return list;
    }

    @Override
    public BillDetailsEntity findById(int id) {
        List<BillDetailsEntity> list = query(BillDetailsSQL.findById, new BillDetailsMapper(), id);
        BillDetailsEntity result = new BillDetailsEntity();
        if (!list.isEmpty()) {
            result = list.get(0);
        }
        return result;
    }

    @Override
    public List<BillDetailsEntity> findByBillId(int id) {
        List<BillDetailsEntity> result = new ArrayList<>();
        List<BillDetailsEntity> list = query(BillDetailsSQL.findByBillId, new BillDetailsMapper(), id);
        if (!list.isEmpty()) {
            result = list;
        }
        return result;
    }

    //update bill
    @Override
    public int updateBillDetails(BillDetailsEntity billDetails) {
        return update(BillDetailsSQL.update, billDetails.getPrice(), billDetails.getQuantity(), billDetails.getProductId(), billDetails.getBillId(), billDetails.getId());
    }

    //delete bill
    @Override
    public int deleteBillDetails(BillDetailsEntity billDetails) {
        BillDAO bill = BillDAO.getInstance();
        int billStatus = bill.findBillById(billDetails.getBillId()).getStatus();
        if (billStatus == 1) //bill is a cart
        {
            return update(BillDetailsSQL.delete, billDetails.getId());
        }
        return 0;
    }

}
