
package com.mrerror.parachut.Models.AllProductStores;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mrerror.parachut.Models.AllProductCategories.Links;
import com.mrerror.parachut.Models.AllProductCategories.Meta;
import com.mrerror.parachut.Models.Datum;

import java.io.Serializable;
import java.util.List;

public class AllProductStores implements Serializable
{

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("links")
    @Expose
    private Links links;
    @SerializedName("meta")
    @Expose
    private Meta meta;
    private final static long serialVersionUID = -7855630973785477946L;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

}
