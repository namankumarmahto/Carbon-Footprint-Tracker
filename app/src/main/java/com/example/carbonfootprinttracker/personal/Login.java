package com.example.carbonfootprinttracker.personal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.example.carbonfootprinttracker.R;
import com.google.firebase.database.*;

public class Login extends AppCompatActivity {
    EditText edUsername, edPassword;
    Button loginButton;
    TextView createAccount;

    private DatabaseReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUsername = findViewById(R.id.login_username);
        edPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        createAccount = findViewById(R.id.create_account);

        // ✅ Correct personal node
        usersRef = FirebaseDatabase.getInstance().getReference("personal_users");

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
                startActivity(new Intent(Login.this, Registation.class)));
    }

    private void loginUser(final String username, final String password) {
        usersRef.orderByChild("username").equalTo(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                        String storedPassword = userSnapshot.child("password").getValue(String.class);
                        if (storedPassword != null && storedPassword.equals(password)) {
                            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login.this, Home_host.class));
                            finish();
                            return;
                        }
                    }
                    Toast.makeText(Login.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Login.this, "User not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(Login.this, "Login Failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
