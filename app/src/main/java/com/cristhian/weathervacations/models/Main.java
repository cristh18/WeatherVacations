package com.cristhian.weathervacations.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Cristhian on 2/14/2016.
 */
public class Main {

    @Expose
    @SerializedName("temp")
    Double temp;

    @Expose
    @SerializedName("pressure")
    Double pressure;

    @Expose
    @SerializedName("humidity")
    Double humidity;

    @Expose
    @SerializedName("temp_min")
    Double temp_min;

    @Expose
    @SerializedName("temp_max")
    Double temp_max;

    @Expose
    @SerializedName("sea_level")
    Double sea_level;

    @Expose
    @SerializedName("grnd_level")
    Double grnd_level;

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(Double temp_min) {
        this.temp_min = temp_min;
    }

    public Double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(Double temp_max) {
        this.temp_max = temp_max;
    }

    public Double getSea_level() {
        return sea_level;
    }

    public void setSea_level(Double sea_level) {
        this.sea_level = sea_level;
    }

    public Double getGrnd_level() {
        return grnd_level;
    }

    public void setGrnd_level(Double grnd_level) {
        this.grnd_level = grnd_level;
    }
}
