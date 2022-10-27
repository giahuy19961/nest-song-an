/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mygroup.nestsonganver2.dao;

import com.mygroup.nestsonganver2.entity.BillEntity;
import java.util.List;

/**
 *
 * @author Silver King
 */
public interface IBillDAO extends IDao<BillEntity> {

// create new bill
    public int createNewBill(BillEntity bill);
    
    public int createNewCart(BillEntity bill);
//------------------------------------------------------------------------------
//find bill
    public List<BillEntity> finndAll();

    public BillEntity findBillById(int id);

    public List<BillEntity> findBillByStatus(int status);

    public List<BillEntity> findBillByCustomerId(int customerId);

    public List<BillEntity> findBillByEmpId(int empId);

//------------------------------------------------------------------------------
// update bill
    public int updateStatus(int id, int status);

    public int updateBill(BillEntity bill);
    public List<BillEntity> findByEmpIdAndStatus(int empId, int status);
    public List<BillEntity> findByCustomerIdAndStatus(int empId, int status);
}
