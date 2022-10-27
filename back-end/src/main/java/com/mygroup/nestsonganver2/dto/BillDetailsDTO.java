/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.dto;

/**
 *
 * @author Silver King
 */
public class BillDetailsDTO {

    private int id;
    private float price;
    private int quantity;
    private ProductDTO product;
    private int billId;

    private static BillDetailsDTO billDetailsDTO = null;

    public static BillDetailsDTO getInstance() {
        if (billDetailsDTO == null) {
            billDetailsDTO = new BillDetailsDTO();
        }
        return billDetailsDTO;
    }

    public BillDetailsDTO() {
    }

    public BillDetailsDTO(int id, float price, int quantity, ProductDTO product, int billId) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.product = product;
        this.billId = billId;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
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

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

}
