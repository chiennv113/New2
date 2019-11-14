
package com.example.anew.Model.ModelViewTicketInDS;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("contents")
    @Expose
    private String contents;
    @SerializedName("images")
    @Expose
    private String images;
    @SerializedName("product")
    @Expose
    private Product product;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("ticket_condition")
    @Expose
    private String ticketCondition;
    @SerializedName("create_time")
    @Expose
    private Integer createTime;
    @SerializedName("accept_time")
    @Expose
    private Object acceptTime;
    @SerializedName("end_time")
    @Expose
    private Object endTime;
    @SerializedName("users")
    @Expose
    private Users users;
    @SerializedName("supporter")
    @Expose
    private Object supporter;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTicketCondition() {
        return ticketCondition;
    }

    public void setTicketCondition(String ticketCondition) {
        this.ticketCondition = ticketCondition;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Object getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(Object acceptTime) {
        this.acceptTime = acceptTime;
    }

    public Object getEndTime() {
        return endTime;
    }

    public void setEndTime(Object endTime) {
        this.endTime = endTime;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Object getSupporter() {
        return supporter;
    }

    public void setSupporter(Object supporter) {
        this.supporter = supporter;
    }

}
