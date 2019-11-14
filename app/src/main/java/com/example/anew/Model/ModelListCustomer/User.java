
package com.example.anew.Model.ModelListCustomer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("email")
    @Expose
    private Object email;
    @SerializedName("avatar")
    @Expose
    private Object avatar;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("phone1")
    @Expose
    private String phone1;
    @SerializedName("phone2")
    @Expose
    private Object phone2;
    @SerializedName("city")
    @Expose
    private Integer city;
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
    private String zalo;
    @SerializedName("zalo_id")
    @Expose
    private Object zaloId;
    @SerializedName("user_type")
    @Expose
    private UserType userType;
    @SerializedName("user_active")
    @Expose
    private Integer userActive;
    @SerializedName("create_time")
    @Expose
    private Integer createTime;
    @SerializedName("create_user")
    @Expose
    private Integer createUser;
    @SerializedName("is_user")
    @Expose
    private Integer isUser;
    @SerializedName("code_email")
    @Expose
    private Object codeEmail;
    @SerializedName("customer")
    @Expose
    private Customer customer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getAvatar() {
        return avatar;
    }

    public void setAvatar(Object avatar) {
        this.avatar = avatar;
    }

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

    public Object getPhone2() {
        return phone2;
    }

    public void setPhone2(Object phone2) {
        this.phone2 = phone2;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
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

    public String getZalo() {
        return zalo;
    }

    public void setZalo(String zalo) {
        this.zalo = zalo;
    }

    public Object getZaloId() {
        return zaloId;
    }

    public void setZaloId(Object zaloId) {
        this.zaloId = zaloId;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Integer getUserActive() {
        return userActive;
    }

    public void setUserActive(Integer userActive) {
        this.userActive = userActive;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Integer getIsUser() {
        return isUser;
    }

    public void setIsUser(Integer isUser) {
        this.isUser = isUser;
    }

    public Object getCodeEmail() {
        return codeEmail;
    }

    public void setCodeEmail(Object codeEmail) {
        this.codeEmail = codeEmail;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
