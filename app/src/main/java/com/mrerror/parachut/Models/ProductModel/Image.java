
package com.mrerror.parachut.Models.ProductModel;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image implements Serializable
{

    @SerializedName("image")
    @Expose
    private String image;
    private final static long serialVersionUID = 8955413674796514231L;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
