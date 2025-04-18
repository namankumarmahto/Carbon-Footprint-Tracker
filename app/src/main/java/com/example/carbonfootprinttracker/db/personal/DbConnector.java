package com.example.carbonfootprinttracker.db.personal;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DbConnector {

    private static final DatabaseReference usersRef =
            FirebaseDatabase.getInstance().getReference("personal_users");

    public static void insertUser(String username, String email, String password, InsertCallback callback) {
        User user = new User(username, email, password);
        usersRef.child(username).setValue(user)
                .addOnSuccessListener(unused -> callback.onSuccess())
                .addOnFailureListener(e -> callback.onFailure(e.getMessage()));
    }

    public static void checkUsernameExists(String username, UsernameCheckCallback callback) {
        usersRef.child(username).get().addOnCompleteListener(task -> {
            callback.onUsernameChecked(task.isSuccessful() && task.getResult().exists());
        });
    }

    public interface UsernameCheckCallback {
        void onUsernameChecked(boolean exists);
    }

    public interface InsertCallback {
        void onSuccess();
        void onFailure(String errorMessage);
    }

    public static class User {
        public String username, email, password;
        public User() {}
        public User(String username, String email, String password) {
            this.username = username;
            this.email = email;
            this.password = password;
        }
    }
}
