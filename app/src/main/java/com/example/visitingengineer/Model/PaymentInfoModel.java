package com.example.visitingengineer.Model;

import java.io.Serializable;

public class PaymentInfoModel implements Serializable {
    String paymentMode , price;

    PaymentInfoModel(){}

    public PaymentInfoModel(String paymentMode , String price){
        this.paymentMode = paymentMode;
        this.price = price;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public String getPrice() {
        return price;
    }
}
