/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.entity;

/**
 *
 * @author Silver King
 */
public class BillDetailsEntity {

    private int id;
    private float price;
    private int quantity;
    private int productId;
    private int billId;

    public BillDetailsEntity() {
    }

    public BillDetailsEntity(int id, float price, int quantity, int productId, int billId) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.productId = productId;
        this.billId = billId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }
    
    
}
