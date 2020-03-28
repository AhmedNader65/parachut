
package com.mrerror.parachut.Models.OffersModel;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private Object image;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("offer")
    @Expose
    private Integer offer;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("is_offer")
    @Expose
    private Integer isOffer;
    @SerializedName("is_recommended")
    @Expose
    private Integer isRecommended;
    @SerializedName("is_mostcommon")
    @Expose
    private Integer isMostcommon;
    @SerializedName("offer_text")
    @Expose
    private String offerText;
    @SerializedName("supermarket_id")
    @Expose
    private Integer supermarketId;
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("offer_mostcommen")
    @Expose
    private Integer offerMostcommen;
    @SerializedName("category_mostcommon")
    @Expose
    private Integer categoryMostcommon;
    @SerializedName("supermarket_mostcommon")
    @Expose
    private Integer supermarketMostcommon;
    @SerializedName("lang")
    @Expose
    private String lang;
    private final static long serialVersionUID = 5283352253287310513L;

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

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getOffer() {
        return offer;
    }

    public void setOffer(Integer offer) {
        this.offer = offer;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIsOffer() {
        return isOffer;
    }

    public void setIsOffer(Integer isOffer) {
        this.isOffer = isOffer;
    }

    public Integer getIsRecommended() {
        return isRecommended;
    }

    public void setIsRecommended(Integer isRecommended) {
        this.isRecommended = isRecommended;
    }

    public Integer getIsMostcommon() {
        return isMostcommon;
    }

    public void setIsMostcommon(Integer isMostcommon) {
        this.isMostcommon = isMostcommon;
    }

    public String getOfferText() {
        return offerText;
    }

    public void setOfferText(String offerText) {
        this.offerText = offerText;
    }

    public Integer getSupermarketId() {
        return supermarketId;
    }

    public void setSupermarketId(Integer supermarketId) {
        this.supermarketId = supermarketId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getOfferMostcommen() {
        return offerMostcommen;
    }

    public void setOfferMostcommen(Integer offerMostcommen) {
        this.offerMostcommen = offerMostcommen;
    }

    public Integer getCategoryMostcommon() {
        return categoryMostcommon;
    }

    public void setCategoryMostcommon(Integer categoryMostcommon) {
        this.categoryMostcommon = categoryMostcommon;
    }

    public Integer getSupermarketMostcommon() {
        return supermarketMostcommon;
    }

    public void setSupermarketMostcommon(Integer supermarketMostcommon) {
        this.supermarketMostcommon = supermarketMostcommon;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

}
