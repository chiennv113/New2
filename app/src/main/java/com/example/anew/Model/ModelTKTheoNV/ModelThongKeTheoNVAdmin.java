
package com.example.anew.Model.ModelTKTheoNV;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelThongKeTheoNVAdmin {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("statistics")
    @Expose
    private Statistics statistics;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

}
