
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
    private final static long serialVersionUID = 5324918742626338162L;
    @SerializedName("order_price")
    @Expose
    private Integer orderPrice;
    @SerializedName("final_price")
    @Expose
    private Integer finalPrice;
    @SerializedName("delivery_shifts")
    @Expose
    private String deliveryShifts;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("order_notes")
    @Expose
    private Object orderNotes;
    @SerializedName("products_notes")
    @Expose
    private Object productsNotes;
    @SerializedName("minimum")
    @Expose
    private Object minimum;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("delgate_price")
    @Expose
    private Object delgatePrice;
    @SerializedName("delivery")
    @Expose
    private Object delivery;
    @SerializedName("orderProducts")
    @Expose
    private List<OrderProduct> orderProducts = null;
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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

    public Object getProductsNotes() {
        return productsNotes;
    }

    public void setProductsNotes(Object productsNotes) {
        this.productsNotes = productsNotes;
    }

    public Object getOrderNotes() {
        return orderNotes;
    }

    public void setOrderNotes(Object orderNotes) {
        this.orderNotes = orderNotes;
    }

    public Object getMinimum() {
        return minimum;
    }

    public void setMinimum(Object minimum) {
        this.minimum = minimum;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getDelgatePrice() {
        return delgatePrice;
    }

    public void setDelgatePrice(Object delgatePrice) {
        this.delgatePrice = delgatePrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
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

}
