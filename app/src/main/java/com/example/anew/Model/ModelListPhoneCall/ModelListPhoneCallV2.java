
package com.example.anew.Model.ModelListPhoneCall;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelListPhoneCallV2 {

    @SerializedName("call")
    @Expose
    private List<CallList> call = null;
    @SerializedName("count")
    @Expose
    private Integer count;

    public List<CallList> getCall() {
        return call;
    }

    public void setCall(List<CallList> call) {
        this.call = call;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
