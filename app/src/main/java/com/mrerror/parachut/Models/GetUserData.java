package com.mrerror.parachut.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetUserData implements Serializable
{
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("background")
    @Expose
    private String background;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("lat")
    @Expose
    private Integer lat;
    @SerializedName("long")
    @Expose
    private Object lang;
    @SerializedName("delivery_time")
    @Expose
    private String delivery_time;
    @SerializedName("minimum")
    @Expose
    private String minimum;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("is_open")
    @Expose
    private Object isOpen;
    @SerializedName("delivery_cost")
    @Expose
    private Object deliveryCost;

}
