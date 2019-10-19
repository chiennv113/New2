
package com.example.anew.Model.ModelListPhoneCallRemind;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelListPhoneCallRemind {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("remind_user")
    @Expose
    private Integer remindUser;
    @SerializedName("call_to")
    @Expose
    private CallTo callTo;
    @SerializedName("remind_time")
    @Expose
    private Integer remindTime;
    @SerializedName("remind_content")
    @Expose
    private String remindContent;
    @SerializedName("create_time")
    @Expose
    private Integer createTime;
    @SerializedName("send")
    @Expose
    private Integer send;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRemindUser() {
        return remindUser;
    }

    public void setRemindUser(Integer remindUser) {
        this.remindUser = remindUser;
    }

    public CallTo getCallTo() {
        return callTo;
    }

    public void setCallTo(CallTo callTo) {
        this.callTo = callTo;
    }

    public Integer getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(Integer remindTime) {
        this.remindTime = remindTime;
    }

    public String getRemindContent() {
        return remindContent;
    }

    public void setRemindContent(String remindContent) {
        this.remindContent = remindContent;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getSend() {
        return send;
    }

    public void setSend(Integer send) {
        this.send = send;
    }

}
