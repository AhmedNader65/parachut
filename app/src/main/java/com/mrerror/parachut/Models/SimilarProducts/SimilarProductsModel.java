
package com.mrerror.parachut.Models.SimilarProducts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SimilarProductsModel implements Serializable
{

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    private final static long serialVersionUID = 2695468728609123412L;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

}
