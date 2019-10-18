
package com.example.anew.Model.ModelSearchCu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Search {


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
    @SerializedName("tickets")
    @Expose
    private List<Object> tickets = null;
    @SerializedName("buykey")
    @Expose
    private List<Object> buykey = null;
    @SerializedName("phonecall")
    @Expose
    private List<Phonecall> phonecall = null;
    @SerializedName("customer")
    @Expose
    private Customer customer;

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

    public List<Object> getTickets() {
        return tickets;
    }

    public void setTickets(List<Object> tickets) {
        this.tickets = tickets;
    }

    public List<Object> getBuykey() {
        return buykey;
    }

    public void setBuykey(List<Object> buykey) {
        this.buykey = buykey;
    }

    public List<Phonecall> getPhonecall() {
        return phonecall;
    }

    public void setPhonecall(List<Phonecall> phonecall) {
        this.phonecall = phonecall;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
