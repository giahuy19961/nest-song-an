package com.mygroup.nestsonganver2.entity;

/**
 *
 * @author Silver King
 */
import java.sql.Date;

public class BillEntity {

    private int id;
    private Date date;
    private int status;
    private int customerId;
    private int empId;
    private float TotalPrice;
    private String address;
    private String phoneNumber;
    private int paymentStatusId;

    public BillEntity() {
    }

    public BillEntity(int id, Date date, int status, int customerId, int empId, float TotalPrice,int paymentStatusId) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.customerId = customerId;
        this.empId = empId;
        this.TotalPrice = TotalPrice;
        this.paymentStatusId = paymentStatusId;
    }

    public BillEntity(int id, Date date, int status, int customerId, int empId, float TotalPrice, String address, String phoneNumber, int paymentStatusId) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.customerId = customerId;
        this.empId = empId;
        this.TotalPrice = TotalPrice;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.paymentStatusId = paymentStatusId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public float getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(float TotalPrice) {
        this.TotalPrice = TotalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getPaymentStatusId() {
        return paymentStatusId;
    }

    public void setPaymentStatusId(int paymentStatusId) {
        this.paymentStatusId = paymentStatusId;
    }
    
    

}

