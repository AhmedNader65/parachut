package com.mrerror.parachut.Models.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserLoginModel implements Serializable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("messages")
    @Expose
    private String messages;

    public UserLoginModel(String status, String messages) {
        this.status = status;
        this.messages = messages;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
