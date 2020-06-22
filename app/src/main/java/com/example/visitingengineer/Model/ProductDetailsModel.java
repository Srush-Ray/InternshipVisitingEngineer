package com.example.visitingengineer.Model;

import java.io.Serializable;

public class ProductDetailsModel implements Serializable {
    String price , productId , productQrCode , quantity , status , productName , productGenericName , orderDate;

    ProductDetailsModel(){}

    public ProductDetailsModel(String productGenericName ,String quantity,String productId,String price,String productQrCode,String orderDate,String productName,String status){
        this.price = price;
        this.productId = productId;
        this.productQrCode = productQrCode;
        this.quantity = quantity;
        this.status = status;
        this.productName = productName;
        this.productGenericName = productGenericName;
        this.orderDate = orderDate;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductGenericName() {
        return productGenericName;
    }

    public String getPrice() {
        return price;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductQrCode() {
        return productQrCode;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }

    public String getOrderDate(){return orderDate;}
}
