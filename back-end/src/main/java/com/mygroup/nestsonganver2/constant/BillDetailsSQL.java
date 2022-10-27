/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.constant;

/**
 *
 * @author Silver King
 */
public class BillDetailsSQL {
    //create bill details
    public static String insertNew = "INSERT INTO BillDetails (price,quantity,productId,billId)\n"
                                       +"VALUES (?,?,?,?)";
    
    //--------------------------------------------------------------------------
    //Retrive bill details data
    public static String findAll = "select * from BillDetails";
    public static String findById = "select * from BillDetails where id =?";
    public static String findByBillId = "select * from BillDetails where billId =?";
    
    //--------------------------------------------------------------------------
    //update bill details
    public static String update = "update BillDetails\n"
            + "set price = ?, quantity = ?, productId = ?, billId = ? \n"
            + "where id=?";
    
    //--------------------------------------------------------------------------
    //delete bill details (need check is bill a cart!)
    public static String delete = "DELETE FROM BillDetails WHERE BillDetails.id=?";
}
