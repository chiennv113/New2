
package com.example.anew.Model.ModelLogin;

import com.example.anew.Model.ModelLogin.Data;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login {
String email;
String password;
String option;

public Login(String email, String password, String option){
    this.email = email;
    this.password = password;
    this.option = option;
}
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
