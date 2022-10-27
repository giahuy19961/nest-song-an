/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.constant;

/**
 *
 * @author ADMIN
 */
public class CategorySQL {
    // get all categories
    public static String getAllCategories="select * from dbo.Category\n";
    // get category name by Id
    public static String getCategoryById="select * from dbo.Category\n" +
            "where id= ?";         
}
