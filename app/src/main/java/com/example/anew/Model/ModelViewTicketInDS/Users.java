
package com.example.anew.Model.ModelViewTicketInDS;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Users {

    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("phone1")
    @Expose
    private String phone1;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("address")
    @Expose
    private Object address;
    @SerializedName("birthday")
    @Expose
    private Object birthday;
    @SerializedName("skype")
    @Expose
    private Object skype;
    @SerializedName("zalo")
    @Expose
    private Object zalo;
    @SerializedName("avatar")
    @Expose
    private Object avatar;
    @SerializedName("customer")
    @Expose
    private Customer customer;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Object getBirthday() {
        return birthday;
    }

    public void setBirthday(Object birthday) {
        this.birthday = birthday;
    }

    public Object getSkype() {
        return skype;
    }

    public void setSkype(Object skype) {
        this.skype = skype;
    }

    public Object getZalo() {
        return zalo;
    }

    public void setZalo(Object zalo) {
        this.zalo = zalo;
    }

    public Object getAvatar() {
        return avatar;
    }

    public void setAvatar(Object avatar) {
        this.avatar = avatar;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
