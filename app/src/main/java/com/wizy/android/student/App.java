package com.wizy.android.student;

import android.app.Application;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class App extends Application {
    private static App context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Montserrat-Regular.otf")
                .setFontAttrId(R.attr.fontPath)
                .build());
      /*  //disk persistence stores the data offline
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);*/
    }

    public static App getContext() {
        return context;
    }
}
