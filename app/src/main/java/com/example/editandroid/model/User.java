package com.example.editandroid.model;

public class User {
    private String email;
    private String uid;
    private String address;

    public User( String email, String uid, String address) {

        this.email = email;
        this.uid = uid;
        this.address = address;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
