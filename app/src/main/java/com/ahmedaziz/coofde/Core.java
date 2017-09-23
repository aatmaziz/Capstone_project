package com.ahmedaziz.coofde;

import android.app.Application;

import com.ahmedaziz.coofde.config.constants;
import com.ahmedaziz.coofde.config.PrefManager;
import com.akhooo.coofde.R;
import com.firebase.client.Firebase;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Logger;
import com.google.android.gms.analytics.Tracker;

import static android.icu.text.CurrencyPluralInfo.getInstance;


/**
 * Created by Ahmed Aziz on 9/3/2017.
 */

public class Core extends Application {
    private static Firebase ref ;
    private Tracker mTracker ;
    @Override
    public void onCreate(){
        super.onCreate();
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
        PrefManager.getInstance().initialize(this);
    }
    public static Firebase getFirebaseRef(){
        if (ref == null){
            ref = new Firebase(constants.FIREBASE_URL);
        }
        return ref ;
    }
    synchronized public Tracker getDefaultTracker(){
        if (mTracker == null){
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            mTracker = analytics.newTracker(R.xml.coofde_tracker);

        }
        return mTracker;
    }
    public void startTracking (){
        if (mTracker == null){
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            mTracker = analytics.newTracker(R.xml.coofde_tracker);
            analytics.enableAutoActivityReports(this);
            analytics.getLogger().setLogLevel(Logger.LogLevel.VERBOSE);

    }
    }
}
