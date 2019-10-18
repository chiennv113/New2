
package com.example.anew.Model.ModelListPhoneCall;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelListPhoneCall {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("note")
    @Expose
    private Object note;
    @SerializedName("call_time")
    @Expose
    private String callTime;
    @SerializedName("customer_feel")
    @Expose
    private String customerFeel;
    @SerializedName("create_time")
    @Expose
    private Integer createTime;
    @SerializedName("new_customer")
    @Expose
    private Integer newCustomer;
    @SerializedName("customer")
    @Expose
    private Customer customer;
    @SerializedName("caller")
    @Expose
    private String caller;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Object getNote() {
        return note;
    }

    public void setNote(Object note) {
        this.note = note;
    }

    public String getCallTime() {
        return callTime;
    }

    public void setCallTime(String callTime) {
        this.callTime = callTime;
    }

    public String getCustomerFeel() {
        return customerFeel;
    }

    public void setCustomerFeel(String customerFeel) {
        this.customerFeel = customerFeel;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getNewCustomer() {
        return newCustomer;
    }

    public void setNewCustomer(Integer newCustomer) {
        this.newCustomer = newCustomer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCaller() {
        return caller;
    }

    public void setCaller(String caller) {
        this.caller = caller;
    }

}
