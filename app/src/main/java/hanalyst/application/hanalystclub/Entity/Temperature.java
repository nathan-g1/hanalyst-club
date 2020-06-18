package hanalyst.application.hanalystclub.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Temperature implements Serializable {

    @SerializedName("scaleType")
    @Expose
    private String scaleType;
    @SerializedName("value")
    @Expose
    private double value;

    public Temperature(String scaleType, double value) {
        this.scaleType = scaleType;
        this.value = value;
    }

    public String getScaleType() {
        return scaleType;
    }

    public void setScaleType(String scaleType) {
        this.scaleType = scaleType;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
