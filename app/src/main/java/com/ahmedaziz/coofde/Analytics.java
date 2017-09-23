package com.ahmedaziz.coofde;

import com.ahmedaziz.coofde.DataBase.Provider;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

/**
 * Created by Ahmed Aziz on 9/22/2017.
 */

public class Analytics {
    public static void startTracking(Core application, String screenName){
        Tracker tracker = application.getDefaultTracker();
        tracker.setScreenName(screenName);
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
    }
}
