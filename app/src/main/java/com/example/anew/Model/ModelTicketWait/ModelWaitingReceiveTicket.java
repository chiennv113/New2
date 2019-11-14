
package com.example.anew.Model.ModelTicketWait;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelWaitingReceiveTicket {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("ticket_id")
    @Expose
    private Integer ticketId;
    @SerializedName("user_pass")
    @Expose
    private UserPass userPass;
    @SerializedName("user_receive")
    @Expose
    private Integer userReceive;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("create_time")
    @Expose
    private Integer createTime;
    @SerializedName("action_time")
    @Expose
    private Object actionTime;
    @SerializedName("tickets")
    @Expose
    private Tickets tickets;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public UserPass getUserPass() {
        return userPass;
    }

    public void setUserPass(UserPass userPass) {
        this.userPass = userPass;
    }

    public Integer getUserReceive() {
        return userReceive;
    }

    public void setUserReceive(Integer userReceive) {
        this.userReceive = userReceive;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Object getActionTime() {
        return actionTime;
    }

    public void setActionTime(Object actionTime) {
        this.actionTime = actionTime;
    }

    public Tickets getTickets() {
        return tickets;
    }

    public void setTickets(Tickets tickets) {
        this.tickets = tickets;
    }

}
