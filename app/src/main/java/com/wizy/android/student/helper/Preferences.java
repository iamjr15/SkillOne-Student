package com.wizy.android.student.helper;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import com.wizy.android.student.App;

public class Preferences {
    private static Preferences instance;
    private final SharedPreferences sharedPreferences;

    private Preferences() {
        sharedPreferences = App.getContext().getSharedPreferences(AppConstants.PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    @NonNull
    public static Preferences getInstance() {
        if (instance == null) {
            instance = new Preferences();
        }
        return instance;
    }
}
