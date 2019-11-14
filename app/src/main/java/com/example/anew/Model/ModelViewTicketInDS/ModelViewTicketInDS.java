
package com.example.anew.Model.ModelViewTicketInDS;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelViewTicketInDS {

    @SerializedName("data")
    @Expose
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
