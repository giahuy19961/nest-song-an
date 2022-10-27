/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.service;

import com.mygroup.nestsonganver2.converter.CategoryConverter;
import com.mygroup.nestsonganver2.dao.impl.CategoryDAO;
import com.mygroup.nestsonganver2.dto.CategoryDTO;
import com.mygroup.nestsonganver2.entity.CategoryEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class CategoryService {
    private static final CategoryDAO categoryDAO = CategoryDAO.getInstance();

    private static CategoryService categoryService;

    public static CategoryService getInstance() {
        if (categoryService == null) {
            categoryService = new CategoryService();
        }
        return categoryService;
    }
    
     public List<CategoryDTO> getAllCategories(){
        List<CategoryDTO> list = new ArrayList<>();
        CategoryDTO categoryDTO;
        List<CategoryEntity> entityList = categoryDAO.getAllCategories();
        if(entityList == null) return null;      
        for (CategoryEntity category : entityList) {
            categoryDTO = CategoryConverter.convertEntitytoDTO(category);
            list.add(categoryDTO);
        }
        return list;
    }
    
    public List<CategoryDTO> getCategoryById(int Id){
        List<CategoryDTO> list = new ArrayList<>();
        CategoryDTO categoryDTO;
        List<CategoryEntity> entityList = categoryDAO.getCategoryById(Id);
        if(entityList == null) return null;      
        for (CategoryEntity category : entityList) {
            categoryDTO = CategoryConverter.convertEntitytoDTO(category);
            list.add(categoryDTO);
        }
        return list;
    }
    
}
