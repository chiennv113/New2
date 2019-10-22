
package com.example.anew.Model.ModelTKTheoNV;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Statistics {

    @SerializedName("phone")
    @Expose
    private List<Phone> phone = null;
    @SerializedName("customernew")
    @Expose
    private List<Customernew> customernew = null;
    @SerializedName("customerold")
    @Expose
    private List<Customerold> customerold = null;

    public List<Phone> getPhone() {
        return phone;
    }

    public void setPhone(List<Phone> phone) {
        this.phone = phone;
    }

    public List<Customernew> getCustomernew() {
        return customernew;
    }

    public void setCustomernew(List<Customernew> customernew) {
        this.customernew = customernew;
    }

    public List<Customerold> getCustomerold() {
        return customerold;
    }

    public void setCustomerold(List<Customerold> customerold) {
        this.customerold = customerold;
    }

}
