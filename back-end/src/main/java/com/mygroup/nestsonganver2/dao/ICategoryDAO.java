/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.dao;

import com.mygroup.nestsonganver2.entity.CategoryEntity;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface ICategoryDAO extends IDao<CategoryEntity> {
    public List<CategoryEntity> getAllCategories();
    public List<CategoryEntity> getCategoryById(int Id);
}
