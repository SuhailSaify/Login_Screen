package com.example.suhail.loginattempt1.Models;

/**
 * Created by Suhail on 12/8/2017.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class student_info {


    @SerializedName("sid")
    @Expose
    private String sid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cid")
    @Expose
    private String cid;
    @SerializedName("optionals")
    @Expose
    private String optionals;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("contact")
    @Expose
    private String contact;
    @SerializedName("password")
    @Expose
    private String password;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getOptionals() {
        return optionals;
    }

    public void setOptionals(String optionals) {
        this.optionals = optionals;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}


