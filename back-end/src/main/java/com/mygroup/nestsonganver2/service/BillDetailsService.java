/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.service;

import com.mygroup.nestsonganver2.converter.BillDetailsConverter;
import com.mygroup.nestsonganver2.dao.impl.BillDetailsDAO;
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
public class BillDetailsService {
    private static final ProductDAO PRODUCT_DAO = ProductDAO.getInstance();
    private static final BillDetailsDAO BILL_DETAILS_DAO = BillDetailsDAO.getInstance();
    private static final BillDetailsConverter BILL_DETAILS_CONVERTER = BillDetailsConverter.getInstance();
    private static BillDetailsService billDetailsService = null;

    public static BillDetailsService getInstance() {
        if (billDetailsService == null) {
            billDetailsService = new BillDetailsService();
        }
        return billDetailsService;
    }

    //--------------------------------------------------------------------------
    //insert new bill details
    public int insertNewBillDetails(BillDetailsDTO billDetails) {
        ProductEntity product = PRODUCT_DAO.getProductById(billDetails.getProduct().getId());
        BillDetailsEntity billDetailsEntity = BILL_DETAILS_CONVERTER.convertDTOtoEntity(billDetails);
        billDetailsEntity.setPrice(product.getBasePrice());
        return BILL_DETAILS_DAO.insertNewBillDetails(billDetailsEntity);
    }

    //--------------------------------------------------------------------------
    //find bill details by...
    public List<BillDetailsDTO> findAll() {
        List<BillDetailsDTO> result = new ArrayList<>();
        List<BillDetailsEntity> listEntity = BILL_DETAILS_DAO.findAll();
        if (!listEntity.isEmpty()) {
            result = BILL_DETAILS_CONVERTER.convertListEntitytoDTO(listEntity);
        }
        return result;
    }

    public List<BillDetailsDTO> findByBillId(int id) {
        List<BillDetailsDTO> result = new ArrayList<>();

        List<BillDetailsEntity> listEntity = BILL_DETAILS_DAO.findByBillId(id);
        if (!listEntity.isEmpty()) {
            result = BILL_DETAILS_CONVERTER.convertListEntitytoDTO(listEntity);
        }
        return result;
    }

    public BillDetailsDTO findById(int id) {
        BillDetailsDTO result = null;
        BillDetailsEntity entity = BILL_DETAILS_DAO.findById(id);
        if (entity != null) {
            result = BILL_DETAILS_CONVERTER.convertEntitytoDTO(entity);
        }
        return result;
    }

    //--------------------------------------------------------------------------
    //update
    public int updateBillDetails(int id, BillDetailsDTO billDetails) {
        billDetails.setId(id);
        return BILL_DETAILS_DAO.updateBillDetails(BILL_DETAILS_CONVERTER.convertDTOtoEntity(billDetails));
    }

    //--------------------------------------------------------------------------
    //delete 
    public int deleteBillDetails(BillDetailsDTO billDetails) {
        return BILL_DETAILS_DAO.deleteBillDetails(BILL_DETAILS_CONVERTER.convertDTOtoEntity(billDetails));
    }
}
