/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.constant;

/**
 *
 * @author dd220
 */
public class RoleSQL {
    
    //Get role by ID
    public static String findById = "SELECT * FROM Roles WHERE id = ?";
    
    //Get all roles
    public static String findAll = "SELECT * FROM Roles";
}
