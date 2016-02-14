package com.cristhian.weathervacations.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Cristhian on 2/14/2016.
 */
public class Main implements Parcelable {

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
    @SerializedName("lon")
    Double lon;

    @Expose
    @SerializedName("lat")
    Double lat;

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

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public static final Parcelable.Creator<Main> CREATOR = new Parcelable.Creator<Main>() {

        public Main createFromParcel(Parcel source) {

            Main main = new Main();
            main.temp = source.readDouble();
            main.pressure = source.readDouble();
            main.humidity = source.readDouble();
            main.temp_min = source.readDouble();
            main.temp_max = source.readDouble();
            main.sea_level = source.readDouble();
            main.grnd_level = source.readDouble();
            main.lon = source.readDouble();
            main.lat = source.readDouble();


            return main;

        }

        public Main[] newArray(int size) {

            return new Main[size];

        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeDouble(temp);
        parcel.writeDouble(pressure);
        parcel.writeDouble(humidity);
        parcel.writeDouble(temp_min);
        parcel.writeDouble(temp_max);
        parcel.writeDouble(sea_level);
        parcel.writeDouble(grnd_level);
        parcel.writeDouble(lon);
        parcel.writeDouble(lat);

    }
}