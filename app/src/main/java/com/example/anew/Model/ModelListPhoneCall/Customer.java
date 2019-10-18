
package com.example.anew.Model.ModelListPhoneCall;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Customer {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("phone1")
    @Expose
    private String phone1;
    @SerializedName("phone2")
    @Expose
    private Object phone2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

}
