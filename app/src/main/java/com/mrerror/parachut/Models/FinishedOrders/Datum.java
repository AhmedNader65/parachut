
package com.mrerror.parachut.Models.FinishedOrders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Datum implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user")
    @Expose
    private Object user;
    @SerializedName("order_price")
    @Expose
    private Integer orderPrice;
    @SerializedName("final_price")
    @Expose
    private Integer finalPrice;
    @SerializedName("delivery_shifts")
    @Expose
    private String deliveryShifts;
    @SerializedName("products_notes")
    @Expose
    private String productsNotes;
    @SerializedName("order_notes")
    @Expose
    private Object orderNotes;
    @SerializedName("minimum")
    @Expose
    private Integer minimum;
    @SerializedName("code")
    @Expose
    private Object code;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("delivery")
    @Expose
    private Object delivery;
    @SerializedName("orderProducts")
    @Expose
    private List<OrderProduct> orderProducts = null;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    private final static long serialVersionUID = -3448869865464621599L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public Integer getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Integer finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getDeliveryShifts() {
        return deliveryShifts;
    }

    public void setDeliveryShifts(String deliveryShifts) {
        this.deliveryShifts = deliveryShifts;
    }

    public String getProductsNotes() {
        return productsNotes;
    }

    public void setProductsNotes(String productsNotes) {
        this.productsNotes = productsNotes;
    }

    public Object getOrderNotes() {
        return orderNotes;
    }

    public void setOrderNotes(Object orderNotes) {
        this.orderNotes = orderNotes;
    }

    public Integer getMinimum() {
        return minimum;
    }

    public void setMinimum(Integer minimum) {
        this.minimum = minimum;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getDelivery() {
        return delivery;
    }

    public void setDelivery(Object delivery) {
        this.delivery = delivery;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
