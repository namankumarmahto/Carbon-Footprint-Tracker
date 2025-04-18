package com.example.carbonfootprinttracker.industry;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.example.carbonfootprinttracker.R;
import com.google.firebase.database.*;

public class Login_Industry extends AppCompatActivity {
    EditText edUsername, edPassword;
    Button loginButton;
    TextView createAccount;

    private DatabaseReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ✅ Use the correct login layout
        setContentView(R.layout.activity_login_industry);

        // ✅ Bind views correctly
        edUsername = findViewById(R.id.login_username);
        edPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        createAccount = findViewById(R.id.create_account);

        // ✅ Point to correct Firebase node
        usersRef = FirebaseDatabase.getInstance().getReference("industry_users");

        loginButton.setOnClickListener(view -> {
            String username = edUsername.getText().toString().trim();
            String password = edPassword.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_SHORT).show();
            } else {
                loginUser(username, password);
            }
        });

        createAccount.setOnClickListener(view ->
                startActivity(new Intent(Login_Industry.this, Registation_Industry.class))
        );
    }

    private void loginUser(final String username, final String password) {
        usersRef.orderByChild("username").equalTo(username)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                String storedPassword = userSnapshot.child("password").getValue(String.class);
                                if (storedPassword != null && storedPassword.equals(password)) {
                                    Toast.makeText(Login_Industry.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Login_Industry.this, Dashboard_Industry.class));
                                    finish();
                                    return;
                                }
                            }
                            Toast.makeText(Login_Industry.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Login_Industry.this, "User not found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        Toast.makeText(Login_Industry.this, "Login Failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
