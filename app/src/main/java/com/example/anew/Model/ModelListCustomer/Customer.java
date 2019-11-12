
package com.example.anew.Model.ModelListCustomer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Customer {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("customer_base")
    @Expose
    private Integer customerBase;
    @SerializedName("software_needed")
    @Expose
    private Object softwareNeeded;
    @SerializedName("service_needed")
    @Expose
    private Object serviceNeeded;
    @SerializedName("other_require")
    @Expose
    private Object otherRequire;
    @SerializedName("other_info")
    @Expose
    private Object otherInfo;
    @SerializedName("customer_type")
    @Expose
    private Integer customerType;
    @SerializedName("status")
    @Expose
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCustomerBase() {
        return customerBase;
    }

    public void setCustomerBase(Integer customerBase) {
        this.customerBase = customerBase;
    }

    public Object getSoftwareNeeded() {
        return softwareNeeded;
    }

    public void setSoftwareNeeded(Object softwareNeeded) {
        this.softwareNeeded = softwareNeeded;
    }

    public Object getServiceNeeded() {
        return serviceNeeded;
    }

    public void setServiceNeeded(Object serviceNeeded) {
        this.serviceNeeded = serviceNeeded;
    }

    public Object getOtherRequire() {
        return otherRequire;
    }

    public void setOtherRequire(Object otherRequire) {
        this.otherRequire = otherRequire;
    }

    public Object getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(Object otherInfo) {
        this.otherInfo = otherInfo;
    }

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
