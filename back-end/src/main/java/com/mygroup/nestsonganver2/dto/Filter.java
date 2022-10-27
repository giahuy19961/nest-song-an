/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.dto;

import java.util.List;


/**
 *
 * @author ADMIN
 */
public class Filter {
    public List<String> name;
    public float lowPrice;
    public float highPrice;
    public float deal;
    public int cateId;

    public Filter() {
    }

    public Filter(List<String> name, float lowPrice, float highPrice, float deal, int cateId) {
        this.name = name;
        this.lowPrice = lowPrice;
        this.highPrice = highPrice;
        this.deal = deal;
        this.cateId = cateId;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public float getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(float lowPrice) {
        this.lowPrice = lowPrice;
    }

    public float getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(float highPrice) {
        this.highPrice = highPrice;
    }

    public float getDeal() {
        return deal;
    }

    public void setDeal(float deal) {
        this.deal = deal;
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }
    
    

   

}
