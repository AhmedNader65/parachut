
package com.mrerror.parachut.Models.DetailsOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DetailsOrderModel implements Serializable {

    private final static long serialVersionUID = -6702204771819863127L;
    @SerializedName("data")
    @Expose
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
