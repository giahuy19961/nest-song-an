/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.dao;

import com.mygroup.nestsonganver2.dto.Filter;
import com.mygroup.nestsonganver2.entity.ProductEntity;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IProductDAO extends IDao<ProductEntity> {

    public List<ProductEntity> showAll();
    
    public List<ProductEntity> getByStatus(int status);

    public List<ProductEntity> searchByName(String keyword);

    public int addNewProduct(ProductEntity product);

    public List<ProductEntity> getProductByCateId(int cateId);

    public ProductEntity getProductById(int Id);
    
    public int updateProduct(ProductEntity product);
    
    public List<ProductEntity> filter(Filter filter);
    
    public List<ProductEntity> getProductByPages(int offset,int fetch);
    
    public int setProductStatus(int isbn, int status) ;
    
    public int substractQuantity(int id, int quantity);
    
    public List<ProductEntity> getAllByPages(int page , int limit);
    
    public int countAllProduct();
}
