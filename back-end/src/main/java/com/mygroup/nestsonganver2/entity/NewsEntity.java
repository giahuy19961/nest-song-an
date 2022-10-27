/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.entity;

/**
 *
 * @author dd220
 */
public class NewsEntity {
    
    private int id;
    private String description;
    private String shortDescription;
    private String title;
    private int empId;

    public NewsEntity() {
    }

    public NewsEntity(int id, String description, String title, String shortDescription, int empId) {
        this.id = id;
        this.description = description;
        this.title = title;
        this.shortDescription = shortDescription;
        this.empId = empId;
    }

    public NewsEntity(String description, String title, String shortDescription, int empId) {
        this.description = description;
        this.title = title;
        this.shortDescription = shortDescription;
        this.empId = empId;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }
    
    
    
}
