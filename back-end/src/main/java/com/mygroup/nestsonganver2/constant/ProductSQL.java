/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.constant;

/**
 *
 * @author ADMIN
 */
public class ProductSQL {

    //add a new product *
    public static String addNewProduct = "insert into dbo.Products (name, quantity, deal, description, basePrice, cateId, status)\n"
            + "Values (?,?,?,?,?,?,?)";
    //get by status
    public static String getByStatus = "select * from dbo.Products\n"
            + "where status = ?";
    //get all by page
    public static String getAllByPages = "select * from dbo.Products\n"
            + "order by id\n"
            + "offset ? row\n"
            + "fetch next ? rows only";
    
    //count all product
    public static String countAllProduct = "select count(id) as total\n"
            + "from dbo.Products\n";
            
    //count by status
    public static String countProductByStatus = "select count(id) as total\n"
            + "from dbo.Products\n"
            + "where status = ?";
    
    //search product by name *
    public static String searchProductByName = "select * from dbo.Products\n"
            + "where lower(name) like lower(?) and status=1";

    //get product by id  *
    public static String getProductById = "select * from dbo.Products\n"
            + "where id = ?";

    //get product by CateId *
    public static String getProductByCateId = "select * from dbo.Products\n"
            + "where cateId = ? and status=1";

    //update Product 
    public static String updateProduct = "update dbo.Products\n"
            + "set name = ?, quantity = ?, deal = ?, description = ?, basePrice = ?, cateId = ?\n"
            + "where id=?";

    //delete product (by set status = 0 in db, when it was 0 change to 1-undelete )
    public static String deleleProduct = "update dbo.Products\n"
            + "set status = ?"
            + "where id=?";

    //show all product *
    public static String showAll = "select * from dbo.Products";

    public static String setProductStatus = "update dbo.Products\n"
            + "set status=?\n"
            + "where id =?\n";

    public static String getProductByPages = "select * from dbo.Products\n"
            + "where status = 1\n"
            + "order by id\n"
            + "offset ? row\n"
            + "fetch next ? rows only";

    public static String SubstractQuantity = "update* from dbo.Products\n"
            + "set status = ?"
            + "where id = ?";
}
