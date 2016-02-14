package com.cristhian.weathervacations.utils;

import android.content.Context;
import android.content.res.Configuration;

import com.cristhian.weathervacations.R;

/**
 * Created by Cristhian on 2/13/2016.
 */
public class Utilies {

    /**
     * @param context
     * @return
     */
    public static boolean isTablet(Context context) {
        return (context.getApplicationContext().getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static String formatTemperature(Context context, double temperature) {
        // Data stored in Celsius by default.  If user prefers to see in Fahrenheit, convert
        // the values here.
        String suffix = "\u00B0";
        // For presentation, assume the user doesn't care about tenths of a degree.
        return String.format(context.getString(R.string.format_temperature), temperature);
    }

}
