/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.constant;

/**
 *
 * @author dd220
 */
public class NewsSQL {
    
    
    //Get news
    public static String findAll = "SELECT * FROM News";
    
    public static String findNewsById = "SELECT * FROM News WHERE id = ?";
    
    //Add news
    public static String add = "INSERT News (description, shortDescription, title, empId)"
                             + "VALUES (?, ?, ?, ?)";
    
    //update news
    public static String update ="UPDATE News\n" +
                                "SET description = ?,\n" +
                                "    shortDescription = ?,\n" +
                                "    title = ?,\n" +
                                "    empId = ?\n" +
                                "WHERE id = ?";
}
