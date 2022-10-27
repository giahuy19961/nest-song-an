/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.constant;

/**
 *
 * @author dd220
 */
public class ImageSQL {
    
    // insert Image
    public static String insertNew = "INSERT Images (path, productId, newsId)\n"
                                   + "VALUES\n"
                                   + "(?, ?, ?)";
    
    //get Image
    public static String findAll = "SELECT * FROM Images";
    public static String findById = "SELECT * FROM Images where id = ?";
    public static String findByProductId = "SELECT * FROM Images where productId = ?";
    public static String findByNewsId = "SELECT * FROM Images where newsId = ?";
   
   //update Image
    public static String updateImageById = "UPDATE Images\n"
                                        + "SET path = ?,\n"
                                        + "    newsId = ?,\n"
                                        + "    productId = ?\n"
                                        + "WHERE id = ? ";
//    public static String updateImageByProductId = "UPDATE [dbo].[Images]"
//                                        + "SET path = ?"
//                                        + "WHERE productId = ? ";
//    public static String updateImageByNewsId = "UPDATE [dbo].[Images]"
//                                        + "SET path = ?"
//                                        + "WHERE id = ? ";
}   
