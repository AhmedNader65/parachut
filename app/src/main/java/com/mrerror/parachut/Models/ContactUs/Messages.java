
package com.mrerror.parachut.Models.ContactUs;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Messages implements Serializable
{

    @SerializedName("messages")
    @Expose
    private List<String> messages = null;
    private final static long serialVersionUID = 9222252195429424158L;

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

}
