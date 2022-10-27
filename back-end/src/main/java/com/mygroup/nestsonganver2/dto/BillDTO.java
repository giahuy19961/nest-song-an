package com.mygroup.nestsonganver2.dto;


import java.sql.Date;
import java.util.List;

/**
 *
 * @author Silver King
 */
public class BillDTO {

    private int id;
    private Date date;
    private int status;
    private int customerId;
    private int empId;
    private List<BillDetailsDTO> listBillDetails;
    private float TotalPrice;
    private String address;
    private String phoneNumber;
    private String paymentStatusCode;
    private int paymentStatusCodeId;
    
    private static BillDTO billDTO = null;

    public static BillDTO getInstance() {
        if (billDTO == null) {
            billDTO = new BillDTO();
        }
        return billDTO;
    }

    public BillDTO() {
    }


    public BillDTO(int id, Date date, int status, int customerId, int empId, List<BillDetailsDTO> listBillDetails,
                   float TotalPrice, String address, String phoneNumber, String paymentStatusCode, int paymentStatusCodeId ) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.customerId = customerId;
        this.empId = empId;
        this.listBillDetails = listBillDetails;
        this.TotalPrice = TotalPrice;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.paymentStatusCode = paymentStatusCode;
        this.paymentStatusCodeId = paymentStatusCodeId;
    }

    public String getPaymentStatusCode() {
        return paymentStatusCode;
    }

    public void setPaymentStatusCode(String paymentStatusCode) {
        this.paymentStatusCode = paymentStatusCode;
    }

    public static BillDTO getBillDTO() {
        return billDTO;
    }

    public static void setBillDTO(BillDTO billDTO) {
        BillDTO.billDTO = billDTO;
    }

    public int getPaymentStatusCodeId() {
        return paymentStatusCodeId;
    }

    public void setPaymentStatusCodeId(int paymentStatusCodeId) {
        this.paymentStatusCodeId = paymentStatusCodeId;
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

    public List<BillDetailsDTO> getListBillDetails() {
        return listBillDetails;
    }

    public void setListBillDetails(List<BillDetailsDTO> listBillDetails) {
        this.listBillDetails = listBillDetails;
    }

    public int getId() {
        return id;
    }

    public float getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(float TotalPrice) {
        this.TotalPrice = TotalPrice;
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

}