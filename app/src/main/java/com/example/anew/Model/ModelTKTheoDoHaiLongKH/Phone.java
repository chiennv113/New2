
package com.example.anew.Model.ModelTKTheoDoHaiLongKH;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Phone {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("customer_id")
    @Expose
    private CustomerId customerId;
    @SerializedName("caller_id")
    @Expose
    private CallerId callerId;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("call_time")
    @Expose
    private Integer callTime;
    @SerializedName("customer_feel")
    @Expose
    private Integer customerFeel;
    @SerializedName("create_time")
    @Expose
    private Integer createTime;
    @SerializedName("new_customer")
    @Expose
    private Integer newCustomer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public void setCustomerId(CustomerId customerId) {
        this.customerId = customerId;
    }

    public CallerId getCallerId() {
        return callerId;
    }

    public void setCallerId(CallerId callerId) {
        this.callerId = callerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getCallTime() {
        return callTime;
    }

    public void setCallTime(Integer callTime) {
        this.callTime = callTime;
    }

    public Integer getCustomerFeel() {
        return customerFeel;
    }

    public void setCustomerFeel(Integer customerFeel) {
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

}
