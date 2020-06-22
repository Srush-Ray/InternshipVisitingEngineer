package com.example.visitingengineer.Model;

import java.io.Serializable;

public class CustomerModels implements Serializable {
    String customerName , customerUid;

    CustomerModels(){}

    public CustomerModels(String customerName , String customerUid){
        this.customerName = customerName;
        this.customerUid = customerUid;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerUid() {
        return customerUid;
    }
}
