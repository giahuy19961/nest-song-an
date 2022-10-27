/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.dao.impl;

import com.mygroup.nestsonganver2.constant.ProductSQL;
import com.mygroup.nestsonganver2.dao.IProductDAO;
import com.mygroup.nestsonganver2.entity.ProductEntity;
import com.mygroup.nestsonganver2.mapper.ProductMapper;
import java.util.List;
import com.mygroup.nestsonganver2.dto.Filter;
import java.text.DecimalFormat;

/**
 *
 * @author ADMIN
 */
public class ProductDAO extends AbstractDAO<ProductEntity> implements IProductDAO {

    private static final ProductMapper productMapper = ProductMapper.getInstance();
    private static ProductDAO productDAO = null;

    public static ProductDAO getInstance() {
        if (productDAO == null) {
            productDAO = new ProductDAO();
        }
        return productDAO;
    }

    @Override
    public List<ProductEntity> showAll() {
        List<ProductEntity> productList = query(ProductSQL.showAll, productMapper);
        return productList.isEmpty() ? null : productList;
    }

    @Override
    public List<ProductEntity> searchByName(String keyword) {
        List<ProductEntity> productList = query(ProductSQL.searchProductByName, productMapper, "%" + keyword + "%");
        return productList.isEmpty() ? null : productList;
    }

    @Override
    public int addNewProduct(ProductEntity product) {
        int id = insert(ProductSQL.addNewProduct, product.getName(), product.getQuantity(), product.getDeal(), product.getDescription(), product.getBasePrice(), product.getCateId(), product.getStatus());
        return id;
    }

    @Override
    public List<ProductEntity> getProductByCateId(int cateId) {
        List<ProductEntity> productList = query(ProductSQL.getProductByCateId, productMapper, cateId);
        return productList.isEmpty() ? null : productList;
    }

    @Override
    public ProductEntity getProductById(int Id) {
        List<ProductEntity> productList = query(ProductSQL.getProductById, productMapper, Id);
        return (productList.isEmpty()) ? null : productList.get(0);
    }

    @Override
    public int updateProduct(ProductEntity product) {
        DecimalFormat df = new DecimalFormat("#.0");
        return update(ProductSQL.updateProduct, product.getName(), product.getQuantity(), df.format(product.getDeal()), product.getDescription(), df.format(product.getBasePrice()), product.getCateId(), product.getId());
    }

    @Override
    public int setProductStatus(int isbn, int status) {
        return update(ProductSQL.setProductStatus, status, isbn);
    }

    @Override
    public List<ProductEntity> filter(Filter filter) {
        List<ProductEntity> productList = query(ProductSQL.showAll, productMapper);
        if (productList == null) {
            return null;
        }
        productList = checkFilter(productList, filter);

        return (productList == null || productList.isEmpty()) ? null : productList;
    }

    private List<ProductEntity> checkFilter(List<ProductEntity> productList, Filter filter) {
        if (filter.getName() != null && !filter.getName().isEmpty()) {
            for (int i = 0; i < productList.size();) {
                int count = 0;
                for (int j = 0; j < filter.getName().size(); j++) {
                    if (productList.get(i).getName().toLowerCase().contains(filter.getName().get(j))) {
                        count++;
                    }

                }
                if (count == 0) {
                    productList.remove(i);
                } else {
                    i++;
                }
            }
        }
        if (filter.getLowPrice() != 0) {
            for (int i = 0; i < productList.size();) {
                if (productList.get(i).getBasePrice() < filter.getLowPrice()) {
                    productList.remove(i);
                } else {
                    i++;
                }
            }
        }
        if (filter.getHighPrice() != 0) {
            for (int i = 0; i < productList.size();) {
                if (productList.get(i).getBasePrice() > filter.getHighPrice()) {
                    productList.remove(i);
                } else {
                    i++;
                }
            }
        }
        if (filter.getCateId() != 0) {
            for (int i = 0; i < productList.size();) {
                if (productList.get(i).getCateId() != filter.getCateId()) {
                    productList.remove(i);
                } else {
                    i++;
                }
            }
        }
        if (filter.getDeal() != 0) {
            for (int i = 0; i < productList.size();) {
                if (productList.get(i).getDeal() != filter.getDeal()) {
                    productList.remove(i);
                } else {
                    i++;
                }
            }
        }
        return productList;
    }

    @Override
    public List<ProductEntity> getProductByPages(int page, int limit) {
        int numberOfProducts = limit * (page - 1);
        List<ProductEntity> productList = query(ProductSQL.getProductByPages, productMapper, numberOfProducts, limit);
        return (productList.isEmpty()) ? null : productList;
    }

    @Override
    public int substractQuantity(int id, int quantity) {
        List<ProductEntity> productList = query(ProductSQL.getProductById, productMapper, id);
        int newQuantity = productList.get(0).getQuantity() - quantity;
        return update(ProductSQL.setProductStatus, newQuantity, id);
    }

    @Override
    public List<ProductEntity> getByStatus(int status) {
        List<ProductEntity> productList = query(ProductSQL.getByStatus,productMapper, status);
        return productList.isEmpty() ? null : productList;
    }

    @Override
    public List<ProductEntity> getAllByPages(int page, int limit) {
        int numberOfProducts = limit * (page - 1);
        List<ProductEntity> productList = query(ProductSQL.getAllByPages, productMapper, numberOfProducts, limit);
        return (productList.isEmpty()) ? null : productList;
    }

    @Override
    public int countAllProduct() {
        int count = queryCount(ProductSQL.countAllProduct);
        return count;
    }
}
