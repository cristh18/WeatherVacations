package com.cristhian.weathervacations.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Cristhian on 2/14/2016.
 */
public class Weather {

    @Expose
    @SerializedName("main")
    Main main;

    @Expose
    @SerializedName("coord")
    Coord coord;

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
}
