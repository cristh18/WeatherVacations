package com.cristhian.weathervacations.utils;

import android.content.Context;
import android.content.res.Configuration;

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

}
