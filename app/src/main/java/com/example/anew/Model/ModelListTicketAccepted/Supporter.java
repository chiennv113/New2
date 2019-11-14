
package com.example.anew.Model.ModelListTicketAccepted;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Supporter {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("email")
    @Expose
    private String email;
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
    private Object city;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("skype")
    @Expose
    private String skype;
    @SerializedName("zalo")
    @Expose
    private String zalo;
    @SerializedName("zalo_id")
    @Expose
    private Object zaloId;
    @SerializedName("user_type")
    @Expose
    private Integer userType;
    @SerializedName("user_active")
    @Expose
    private Integer userActive;
    @SerializedName("create_time")
    @Expose
    private Integer createTime;
    @SerializedName("create_user")
    @Expose
    private Object createUser;
    @SerializedName("is_user")
    @Expose
    private Integer isUser;
    @SerializedName("code_email")
    @Expose
    private Object codeEmail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
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

    public Object getCity() {
        return city;
    }

    public void setCity(Object city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
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

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
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

    public Object getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Object createUser) {
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

}
