
package com.example.anew.Model.ModelViewTicketInDS;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Customer {

    @SerializedName("customer_base")
    @Expose
    private Integer customerBase;
    @SerializedName("other_info")
    @Expose
    private Object otherInfo;

    public Integer getCustomerBase() {
        return customerBase;
    }

    public void setCustomerBase(Integer customerBase) {
        this.customerBase = customerBase;
    }

    public Object getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(Object otherInfo) {
        this.otherInfo = otherInfo;
    }

}
