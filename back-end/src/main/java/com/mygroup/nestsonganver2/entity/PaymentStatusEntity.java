/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygroup.nestsonganver2.entity;

/**
 *
 * @author huy
 */
public class PaymentStatusEntity {
     private int id;
     private String code;

    public PaymentStatusEntity(int id, String code) {
        this.id = id;
        this.code = code;
    }

    public PaymentStatusEntity() {
    }
     
     

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
     
     
}
