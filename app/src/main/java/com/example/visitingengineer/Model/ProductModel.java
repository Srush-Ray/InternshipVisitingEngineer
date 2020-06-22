package com.example.visitingengineer.Model;

import java.io.Serializable;

public class ProductModel implements Serializable {

    private String brand, mrp, categoryUid, productDetail, productGenricName, productName, uid;
    private Integer quantity=0;
    public ProductModel() {

    }

    public ProductModel(String brand, String mrp, String categoryUid, String productDetail, String productGenricName, String productName, String uid) {
        this.brand = brand;
        this.mrp = mrp;
        this.categoryUid = categoryUid;
        this.productDetail = productDetail;
        this.productGenricName = productGenricName;
        this.productName = productName;
        this.uid = uid;
        quantity = 0;
    }

    public void setQuantity(){ quantity++;}

    public String getBrand() {
        return brand;
    }

    public String getMrp() {
        return mrp;
    }

    public String getCategoryUid() {
        return categoryUid;
    }

    public String getProductDetail() {
        return productDetail;
    }

    public String getProductGenricName() {
        return productGenricName;
    }

    public String getProductName() {
        return productName;
    }

    public String getUid() {
        return uid;
    }

    public Integer getQuantity(){return quantity;}

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
