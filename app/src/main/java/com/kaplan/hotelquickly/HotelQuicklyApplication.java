package com.kaplan.hotelquickly;

import android.app.Application;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by kaplanfatt on 09/01/16.
 */
public class HotelQuicklyApplication extends Application {

    private static HotelQuicklyApplication singleton;

    public static HotelQuicklyApplication getInstance() {
        if (singleton == null)
            singleton = new HotelQuicklyApplication();
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
