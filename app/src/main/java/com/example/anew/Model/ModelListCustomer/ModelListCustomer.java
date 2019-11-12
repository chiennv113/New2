
package com.example.anew.Model.ModelListCustomer;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelListCustomer {

    @SerializedName("users")
    @Expose
    private List<User> users = null;
    @SerializedName("count")
    @Expose
    private Integer count;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
