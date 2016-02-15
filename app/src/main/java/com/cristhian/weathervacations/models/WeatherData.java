package com.cristhian.weathervacations.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Cristhian on 2/14/2016.
 */
public class WeatherData {

    @Expose
    @SerializedName("main")
    Main main;

    @Expose
    @SerializedName("coord")
    Coord coord;

    @Expose
    @SerializedName("weather")
    List<Weather> weather;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }
}
