
package com.mrerror.parachut.Models.ContactUs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ContactUsModel implements Serializable
{

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("messages")
    @Expose
    private Messages messages;
    private final static long serialVersionUID = 1810298373885314041L;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Messages getMessages() {
        return messages;
    }

    public void setMessages(Messages messages) {
        this.messages = messages;
    }

}
