package com.wizy.android.student.firebase;

public class FirebaseHelper {
    private static FirebaseHelper instance;

    public static FirebaseHelper getInstance() {
        if (instance == null) {
            instance = new FirebaseHelper();
        }
        return instance;
    }
}
