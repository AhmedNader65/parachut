
package com.mrerror.parachut.Models.LogIn;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    private final static long serialVersionUID = 8598489767603856371L;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("email_verified_at")
    @Expose
    private Object emailVerifiedAt;
    @SerializedName("verify_code")
    @Expose
    private Integer verifyCode;
    @SerializedName("settings")
    @Expose
    private List<Object> settings = null;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("image")
    @Expose
    private Object image;
    @SerializedName("background")
    @Expose
    private Object background;
    @SerializedName("delivery_time")
    @Expose
    private Object deliveryTime;
    @SerializedName("minimum")
    @Expose
    private Object minimum;
    @SerializedName("location")
    @Expose
    private Object location;
    @SerializedName("is_open")
    @Expose
    private Object isOpen;
    @SerializedName("delivery_cost")
    @Expose
    private Object deliveryCost;
    @SerializedName("type")
    @Expose
    private Object type;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("forget_code")
    @Expose
    private Object forgetCode;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("payment")
    @Expose
    private Integer payment;
    @SerializedName("lat")
    @Expose
    private Integer lat;
    @SerializedName("role_id")
    @Expose
    private Integer roleId;
    @SerializedName("lang")
    @Expose
    private Object lang;
    @SerializedName("email")
    @Expose
    private Object email;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("avatar")
    @Expose
    private String avatar;

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

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getEmailVerifiedAt() {
        return emailVerifiedAt;
    }

    public void setEmailVerifiedAt(Object emailVerifiedAt) {
        this.emailVerifiedAt = emailVerifiedAt;
    }

    public Integer getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(Integer verifyCode) {
        this.verifyCode = verifyCode;
    }

    public List<Object> getSettings() {
        return settings;
    }

    public void setSettings(List<Object> settings) {
        this.settings = settings;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public Object getBackground() {
        return background;
    }

    public void setBackground(Object background) {
        this.background = background;
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

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Object getForgetCode() {
        return forgetCode;
    }

    public void setForgetCode(Object forgetCode) {
        this.forgetCode = forgetCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
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

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
