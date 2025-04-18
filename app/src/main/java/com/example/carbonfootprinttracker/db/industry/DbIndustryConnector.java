package com.example.carbonfootprinttracker.db.industry;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DbIndustryConnector {

    private static final DatabaseReference usersRef =
            FirebaseDatabase.getInstance().getReference("industry_users");

    public static void insertUser(String username, String email, String password, String companyName,
                                  String companyType, String gstNo, String phoneNo,
                                  InsertCallback callback) {
        IndustryUser user = new IndustryUser(username, email, password, companyName, companyType, gstNo, phoneNo);
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

    public static class IndustryUser {
        public String username, email, password, companyName, companyType, gstNo, phoneNo;

        public IndustryUser() {} // Required for Firebase

        public IndustryUser(String username, String email, String password, String companyName,
                            String companyType, String gstNo, String phoneNo) {
            this.username = username;
            this.email = email;
            this.password = password;
            this.companyName = companyName;
            this.companyType = companyType;
            this.gstNo = gstNo;
            this.phoneNo = phoneNo;
        }
    }
}
