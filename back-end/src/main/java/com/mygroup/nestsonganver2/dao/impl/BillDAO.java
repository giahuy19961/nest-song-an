/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.dao.impl;

import com.mygroup.nestsonganver2.constant.BillSQL;
import com.mygroup.nestsonganver2.dao.IBillDAO;
import com.mygroup.nestsonganver2.entity.BillEntity;
import com.mygroup.nestsonganver2.mapper.BillMapper;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Silver King
 */
public class BillDAO extends AbstractDAO<BillEntity> implements IBillDAO {

    private static BillDAO billDAO = null;

    public static BillDAO getInstance() {
        if (billDAO == null) {
            billDAO = new BillDAO();
        }
        return billDAO;
    }

    //create new bill
    @Override
    public int createNewBill(BillEntity bill) {
        int id = insert(BillSQL.insertNew, bill.getDate(), bill.getStatus(), bill.getCustomerId(), bill.getEmpId(),
                         bill.getTotalPrice(), bill.getAddress(), bill.getPhoneNumber(), bill.getPaymentStatusId());
        return id;
    }

    @Override
    public int createNewCart(BillEntity bill) {
        int id = insert(BillSQL.insertNewCart, bill.getDate(), bill.getStatus(), bill.getCustomerId(), bill.getEmpId());
        return id;
    }

    //--------------------------------------------------------------------------
    //find bill by.....
    @Override
    public List<BillEntity> finndAll() {
        List<BillEntity> list = new ArrayList<>();
        list = query(BillSQL.findAll, new BillMapper());
        return list;
    }

    @Override
    public BillEntity findBillById(int id) {
        BillEntity result = new BillEntity();
        try {
            List<BillEntity> list = query(BillSQL.findById, new BillMapper(), id);
            if (!list.isEmpty()) {
                result = list.get(0);
            }
            return result;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return result;
        }

    }

    @Override
    public List<BillEntity> findBillByStatus(int status) {
        List<BillEntity> list = new ArrayList<>();
        list = query(BillSQL.findByStatus, new BillMapper(), status);
        return list;
    }

    @Override
    public List<BillEntity> findBillByCustomerId(int customerId) {
        List<BillEntity> list = new ArrayList<>();
        list = query(BillSQL.findByCustomerId, new BillMapper(), customerId);
        return list;
    }

    @Override
    public List<BillEntity> findBillByEmpId(int empId) {
        List<BillEntity> list = new ArrayList<>();
        list = query(BillSQL.findByEmpId, new BillMapper(), empId);
        return list;
    }

    public BillEntity findLastBill(int userId) {
        List<BillEntity> list = new ArrayList<>();
        list = query("SELECT TOP 1 * FROM Bills WHERE [customerId] = ? ORDER BY id DESC ", new BillMapper(), userId);
        if (list.isEmpty() || list == null) {
            return null;
        }
        return list.get(0);
    }

    //--------------------------------------------------------------------------
    // update bill
    @Override
    public int updateBill(BillEntity bill) {
        int result = update(BillSQL.updateBill, bill.getDate(), bill.getStatus(), bill.getCustomerId(), bill.getEmpId(),
                            bill.getTotalPrice(), bill.getAddress(), bill.getPhoneNumber(), bill.getPaymentStatusId(), bill.getId());
        return result;
    }

    //delete (update status)
    @Override
    public int updateStatus(int id, int status) {
        int oldStatus = getInstance().findBillById(id).getStatus();
        if (oldStatus == 3) {
            return 0;
        }
        int result = update(BillSQL.updateStatus, status, id);
        return result;
    }

    @Override
    public List<BillEntity> findByEmpIdAndStatus(int empId, int status) {
        List<BillEntity> list = new ArrayList<>();
        list = query(BillSQL.findByEmpIdAndStatus, new BillMapper(), empId, status);
        return list;
    }

    @Override
    public List<BillEntity> findByCustomerIdAndStatus(int empId, int status) {
        List<BillEntity> list = new ArrayList<>();
        list = query(BillSQL.findByCustomerIdAndStatus, new BillMapper(), empId, status);
        return list;
    }
}
