package com.firas.TheMovieDbApp;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.SystemClock;
import android.preference.PreferenceManager;

import java.util.Calendar;
import java.util.Date;



/**
 * Singleton class (note that we have one instance per application process) that plays the role
 * of Service Locator, decoupling the remaining code base of the concrete service provider types.
 */
public class FirasApplication extends Application {



    @Override
    public void onCreate() {
        super.onCreate();





    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }




}
