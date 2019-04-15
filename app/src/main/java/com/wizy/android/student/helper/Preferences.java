package com.wizy.android.student.helper;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import com.google.gson.Gson;
import com.wizy.android.student.App;
import com.wizy.android.student.model.Student;

public class Preferences {
    private static Preferences instance;
    private final SharedPreferences sharedPreferences;
    private final String STUDENT_PREF = "wizy.student";

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

    public void saveStudent(Student student) {
        String json = new Gson().toJson(student);
        sharedPreferences.edit().putString(STUDENT_PREF, json).apply();
    }

    public Student getStudent() {
        String json = sharedPreferences.getString(STUDENT_PREF, "");
        return new Gson().fromJson(json, Student.class);
    }

}
