package com.example.visitingengineer;

public class CustomerModel {

    private String uid, userName, email;

    public CustomerModel() {

    }

    public CustomerModel(String uid, String userName, String email) {
        this.uid = uid;
        this.userName = userName;
        this.email = email;
    }


    public String getUid() {
        return uid;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

}
