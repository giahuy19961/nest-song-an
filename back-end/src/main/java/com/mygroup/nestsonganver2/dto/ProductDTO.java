/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ProductDTO {

    private int id;
    private String name;
    private int quantity;
    private float deal;
    private String description;
    private float basePrice;
    private int cateId;
    private int status;
    private String image;

    private List<ImageDTO> listImages = new ArrayList<>();

    public ProductDTO() {
    }

    public ProductDTO(int id, String name, int quantity, float deal, String description, float basePrice, int cateId, int status) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.deal = deal;
        this.description = description;
        this.basePrice = basePrice;
        this.cateId = cateId;
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getDeal() {
        return deal;
    }

    public void setDeal(float deal) {
        this.deal = deal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(float basePrice) {
        this.basePrice = basePrice;
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ImageDTO> getListImages() {
        return listImages;
    }

    public void setListImages(List<ImageDTO> listImages) {
        this.listImages = listImages;
    }

}
