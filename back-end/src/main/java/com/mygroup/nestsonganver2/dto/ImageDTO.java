/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.dto;

import com.mygroup.nestsonganver2.entity.*;

/**
 *
 * @author dd220
 */
public class ImageDTO {
    
    private int id;
    private String ImgPath;
    private int productId;
    private int newsId;

    public ImageDTO() {
    }

    public ImageDTO(int id, String ImgPath) {
        this.id = id;
        this.ImgPath = ImgPath;
    }

    public ImageDTO(int id, String ImgPath, int productId, int newsId) {
        this.id = id;
        this.ImgPath = ImgPath;
        this.productId = productId;
        this.newsId = newsId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgPath() {
        return ImgPath;
    }

    public void setImgPath(String ImgPath) {
        this.ImgPath = ImgPath;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }
    
    
}
