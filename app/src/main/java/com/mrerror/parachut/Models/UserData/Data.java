
package com.mrerror.parachut.Models.UserData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Data implements Serializable {

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
    private final static long serialVersionUID = 5392649026173621499L;
    @SerializedName("long")
    @Expose
    private Integer _long;
    @SerializedName("delivery_time")
    @Expose
    private Object deliveryTime;
    @SerializedName("minimum")
    @Expose
    private Object minimum;
    @SerializedName("location")
    @Expose
    private Object location;
    @SerializedName("delivery_cost")
    @Expose
    private Object deliveryCost;
    @SerializedName("is_open")
    @Expose
    private Boolean isOpen;

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

    public Integer getLong() {
        return _long;
    }

    public void setLong(Integer _long) {
        this._long = _long;
    }

    public Object getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Object deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Object getMinimum() {
        return minimum;
    }

    public void setMinimum(Object minimum) {
        this.minimum = minimum;
    }

    public Object getLocation() {
        return location;
    }

    public void setLocation(Object location) {
        this.location = location;
    }

    public Boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }

    public Object getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(Object deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

}
