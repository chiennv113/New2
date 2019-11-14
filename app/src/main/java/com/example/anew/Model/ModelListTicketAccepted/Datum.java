
package com.example.anew.Model.ModelListTicketAccepted;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("status")
    @Expose
    private String status;
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
    private Integer acceptTime;
    @SerializedName("end_time")
    @Expose
    private Object endTime;
    @SerializedName("supporter")
    @Expose
    private Supporter supporter;
    @SerializedName("customer")
    @Expose
    private Customer customer;
    @SerializedName("lastAnswer")
    @Expose
    private List<Object> lastAnswer = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Integer getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(Integer acceptTime) {
        this.acceptTime = acceptTime;
    }

    public Object getEndTime() {
        return endTime;
    }

    public void setEndTime(Object endTime) {
        this.endTime = endTime;
    }

    public Supporter getSupporter() {
        return supporter;
    }

    public void setSupporter(Supporter supporter) {
        this.supporter = supporter;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Object> getLastAnswer() {
        return lastAnswer;
    }

    public void setLastAnswer(List<Object> lastAnswer) {
        this.lastAnswer = lastAnswer;
    }

}
