package com.wizy.android.student.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseHelper {
    private static FirebaseHelper instance;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    public static FirebaseHelper getInstance() {
        if (instance == null) {
            instance = new FirebaseHelper();
            instance.database = FirebaseDatabase.getInstance();
            instance.reference = instance.database.getReference("students");
        }
        return instance;
    }

    public DatabaseReference getStudentReference() {
        return reference;
    }
}
