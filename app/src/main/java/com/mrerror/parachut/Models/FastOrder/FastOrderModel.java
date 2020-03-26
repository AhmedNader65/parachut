
package com.mrerror.parachut.Models.FastOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FastOrderModel implements Serializable {

    private final static long serialVersionUID = -889977993950768333L;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("fast")
    @Expose
    private Fast fast;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Fast getFast() {
        return fast;
    }

    public void setFast(Fast fast) {
        this.fast = fast;
    }

}
