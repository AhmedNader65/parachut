
package com.mrerror.parachut.Models.NotificationsModel;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetNotification implements Serializable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("time_creates")
    @Expose
    private String timeCreates;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("image")
    @Expose
    private String image;
    private final static long serialVersionUID = -6835520501296716945L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimeCreates() {
        return timeCreates;
    }

    public void setTimeCreates(String timeCreates) {
        this.timeCreates = timeCreates;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
