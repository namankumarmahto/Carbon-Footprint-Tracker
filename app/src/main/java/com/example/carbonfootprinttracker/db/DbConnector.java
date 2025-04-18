package com.example.carbonfootprinttracker.db;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DbConnector {

    private static final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static final DatabaseReference usersRef = database.getReference("users");

    // Firebase-compatible insert method
    public static void insertUser(String username, String email, String password, InsertCallback callback) {
        User user = new User(username, email, password);

        usersRef.push().setValue(user)
                .addOnSuccessListener(aVoid -> {
                    if (callback != null) callback.onSuccess();
                })
                .addOnFailureListener(e -> {
                    if (callback != null) callback.onFailure(e.getMessage());
                });
    }

    // Callback interface for async result
    public interface InsertCallback {
        void onSuccess();
        void onFailure(String errorMessage);
    }

    // User data model for Firebase
    public static class User {
        public String username;
        public String email;
        public String password;

        public User() {
        }

        public User(String username, String email, String password) {
            this.username = username;
            this.email = email;
            this.password = password;
        }
    }
}
