
package com.mrerror.parachut.Models.ProductModel;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mrerror.parachut.Models.Datum;

public class DetailsProductModel implements Serializable
{

    @SerializedName("data")
    @Expose
    private Datum data;
    @SerializedName("image")
    @Expose
    private List<Image> image = null;
    private final static long serialVersionUID = -2105664626696619205L;

    public Datum getData() {
        return data;
    }

    public void setData(Datum data) {
        this.data = data;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

}
