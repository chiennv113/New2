
package com.example.anew.Model.ModelTKTheoTatcaDoHaiLong;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelThongKeTheoTatCaDoHaiLongCuaKhachAdmin {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("numbOfCall")
    @Expose
    private List<NumbOfCall> numbOfCall = null;
    @SerializedName("customernew")
    @Expose
    private List<Customernew> customernew = null;
    @SerializedName("customerold")
    @Expose
    private List<Customerold> customerold = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<NumbOfCall> getNumbOfCall() {
        return numbOfCall;
    }

    public void setNumbOfCall(List<NumbOfCall> numbOfCall) {
        this.numbOfCall = numbOfCall;
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
