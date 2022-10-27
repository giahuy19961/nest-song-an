/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygroup.nestsonganver2.dto;

import com.mygroup.nestsonganver2.entity.RoleEntity;
import java.sql.Date;

/**
 *
 * @author huy
 */

public class UserDTO {
    
    private static UserDTO userDTO = null;
    
    public static UserDTO getInstance(){
        if(userDTO == null){
            userDTO = new UserDTO();
        }
        return userDTO;
    }
    
    private int id;
    private String username;
    private String fullname;
    private Date dateOfBirth;
    private String phoneNumber;    
    private String address;
    private String password;
    private RoleEntity role;
    private int paymentStatusCodeId;

    //missing list employee

    public UserDTO() {
    }

    public UserDTO(int id, String username, String fullname, Date dateOfBirth, String phoneNumber, String address, String password, RoleEntity role) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.password = password;
        this.role = role;
     
    }

    public int getPaymentStatusCodeId() {
        return paymentStatusCodeId;
    }

    public void setPaymentStatusCodeId(int paymentStatusCodeId) {
        this.paymentStatusCodeId = paymentStatusCodeId;
    }
    
    

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public static UserDTO getUserDTO() {
        return userDTO;
    }

    public static void setUserDTO(UserDTO userDTO) {
        UserDTO.userDTO = userDTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    } 

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
