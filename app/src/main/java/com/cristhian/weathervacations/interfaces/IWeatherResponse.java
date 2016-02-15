package com.cristhian.weathervacations.interfaces;

import com.cristhian.weathervacations.models.WeatherData;

/**
 * Created by Cristhian on 2/14/2016.
 */
public interface IWeatherResponse {

    public void weatherResponse(Boolean response, WeatherData weather);
}
