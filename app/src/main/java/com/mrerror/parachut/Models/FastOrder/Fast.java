
package com.mrerror.parachut.Models.FastOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Fast implements Serializable {

    private final static long serialVersionUID = -5459370819341492072L;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("fast_order")
    @Expose
    private String fastOrder;
    @SerializedName("id")
    @Expose
    private Integer id;

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

    public String getFastOrder() {
        return fastOrder;
    }

    public void setFastOrder(String fastOrder) {
        this.fastOrder = fastOrder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
