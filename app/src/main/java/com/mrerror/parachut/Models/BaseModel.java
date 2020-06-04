package com.mrerror.parachut.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BaseModel implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;

    private final static long serialVersionUID = 192527351368027205L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



}