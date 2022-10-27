 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.dao.impl;

import com.mygroup.nestsonganver2.constant.CategorySQL;
import com.mygroup.nestsonganver2.constant.ProductSQL;
import com.mygroup.nestsonganver2.dao.ICategoryDAO;
import com.mygroup.nestsonganver2.entity.CategoryEntity;
import com.mygroup.nestsonganver2.entity.ProductEntity;
import com.mygroup.nestsonganver2.mapper.CategoryMapper;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class CategoryDAO extends AbstractDAO<CategoryEntity> implements ICategoryDAO {
    private static CategoryMapper categoryMapper = CategoryMapper.getInstance();
    private static CategoryDAO categoryDAO = null;

    public static CategoryDAO getInstance() {
        if (categoryDAO == null) {
            categoryDAO = new CategoryDAO();
        }
        return categoryDAO;
    }
    
     @Override
    public List<CategoryEntity> getAllCategories() {
        List<CategoryEntity> categoryList = query(CategorySQL.getAllCategories, categoryMapper);
        return categoryList.isEmpty() ? null : categoryList;
    }
    
      @Override
    public List<CategoryEntity> getCategoryById(int Id) {
        List<CategoryEntity> CategoryList = query(CategorySQL.getCategoryById, categoryMapper, Id);
        return CategoryList.isEmpty() ? null : CategoryList;
    }
}
