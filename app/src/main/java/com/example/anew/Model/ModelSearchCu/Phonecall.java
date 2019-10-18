
package com.example.anew.Model.ModelSearchCu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Phonecall {

    @SerializedName("customer_id")
    @Expose
    private Integer customerId;
    @SerializedName("call_time")
    @Expose
    private Integer callTime;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("caller_id")
    @Expose
    private CallerId callerId;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getCallTime() {
        return callTime;
    }

    public void setCallTime(Integer callTime) {
        this.callTime = callTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public CallerId getCallerId() {
        return callerId;
    }

    public void setCallerId(CallerId callerId) {
        this.callerId = callerId;
    }

}
