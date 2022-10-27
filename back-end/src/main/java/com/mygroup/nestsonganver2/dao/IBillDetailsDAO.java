/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mygroup.nestsonganver2.dao;

import com.mygroup.nestsonganver2.entity.BillDetailsEntity;
import java.util.List;

/**
 *
 * @author Silver King
 */
public interface IBillDetailsDAO extends IDao<BillDetailsEntity> {

    //create bill details
    public int insertNewBillDetails(BillDetailsEntity bill);

    //--------------------------------------------------------------------------
    //retrive bill details data
    public List<BillDetailsEntity> findAll();

    public BillDetailsEntity findById(int id);

    public List<BillDetailsEntity> findByBillId(int id);

    //--------------------------------------------------------------------------
    //update bill details
    public int updateBillDetails(BillDetailsEntity billDetails);

    //--------------------------------------------------------------------------
    //delete
    public int deleteBillDetails(BillDetailsEntity billDetails);
}
