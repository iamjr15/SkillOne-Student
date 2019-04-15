package com.wizy.android.student.firebase;

import com.google.firebase.firestore.FirebaseFirestore;

public class FireBaseHelper {
    private static FireBaseHelper instance;
    private FirebaseFirestore db;

    public static FireBaseHelper getInstance() {
        if (instance == null) {
            instance = new FireBaseHelper();
            instance.db = FirebaseFirestore.getInstance();
        }
        return instance;
    }

    public FirebaseFirestore getDb() {
        return db;
    }
}
