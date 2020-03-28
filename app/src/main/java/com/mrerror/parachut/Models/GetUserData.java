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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getLat() {
        return lat;
    }

    public void setLat(Integer lat) {
        this.lat = lat;
    }

    public Object getLang() {
        return lang;
    }

    public void setLang(Object lang) {
        this.lang = lang;
    }

    public String getDelivery_time() {
        return delivery_time;
    }

    public void setDelivery_time(String delivery_time) {
        this.delivery_time = delivery_time;
    }

    public String getMinimum() {
        return minimum;
    }

    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Object getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Object isOpen) {
        this.isOpen = isOpen;
    }

    public Object getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(Object deliveryCost) {
        this.deliveryCost = deliveryCost;
    }
}
