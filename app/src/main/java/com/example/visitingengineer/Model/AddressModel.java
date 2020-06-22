package com.example.visitingengineer.Model;

import java.io.Serializable;

public class AddressModel implements Serializable {

    String area , pincode , flat , townCity , fullName , mobileNo , state , landmark;

    AddressModel(){}

    public AddressModel(String area,String flat,String fullName,String landmark,String mobileNo,String pincode,String state,String townCity){
        this.area = area;
        this.pincode = pincode;
        this.flat = flat;
        this.townCity = townCity;
        this.fullName = fullName;
        this.mobileNo = mobileNo;
        this.state = state;
        this.landmark = landmark;
    }

    public String getArea() {
        return area;
    }

    public String getPincode() {
        return pincode;
    }

    public String getFlat() {
        return flat;
    }

    public String getTownCity() {
        return townCity;
    }

    public String getFullName() {
        return fullName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getState() {
        return state;
    }

    public String getLandmark() {
        return landmark;
    }
}
