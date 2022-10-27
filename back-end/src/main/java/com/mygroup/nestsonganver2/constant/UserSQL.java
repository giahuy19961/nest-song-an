/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygroup.nestsonganver2.constant;

/**
 *
 * @author huy
 */
public class UserSQL {
    
    // Create user
    
    public static String insertNew = "INSERT  [dbo].[Users] (fullname, phoneNumber, address, username, password)\n"
                                   + "VALUES\n"
                                   + "(?, ?, ?, ?, ?)";
    
    // ------------------------------------------------------------------------
    
    // Retrive user data
    
    public static String findAll = "select * from Users where status = 1";
    public static String findById = "select * from Users where id = ? and status = 1";   
    public static String login = "select * from Users where username = ? and password = ? and status = 1";
    public static String findByUsername = "select * from Users where username = ?";
    
    // ------------------------------------------------------------------------
    
    // Update user
    
     public static String updateStatus = "update Users set status = ? where id = ?";
     public static String updateUser = "update Users \n"
                                     + "set fullname = ? , dateOfBirth = ? , phoneNumber = ? , address = ? \n"
                                     + "where id = ? ";
     public static String updatePassword ="update Users \n"
                                     + "set password = ? \n"
                                     + "where id = ? ";
    
    // ------------------------------------------------------------------------ 


    
}