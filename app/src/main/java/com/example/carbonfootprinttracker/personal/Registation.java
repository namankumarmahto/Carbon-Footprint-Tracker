package com.example.carbonfootprinttracker.personal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.example.carbonfootprinttracker.R;
import com.example.carbonfootprinttracker.db.personal.DbConnector;


public class Registation extends AppCompatActivity {

    EditText edUsername, edEmail, edPassword, edPassword2;
    Button registorButton;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registation);

        edUsername = findViewById(R.id.registation_username);
        edEmail = findViewById(R.id.registation_email);
        edPassword = findViewById(R.id.registation_password);
        edPassword2 = findViewById(R.id.registation_password2);
        registorButton = findViewById(R.id.registation_button);
        login = findViewById(R.id.registation_login);

        login.setOnClickListener(view ->
                startActivity(new Intent(Registation.this, Login.class)));

        registorButton.setOnClickListener(view -> {
            String username = edUsername.getText().toString().trim();
            String email = edEmail.getText().toString().trim();
            String password = edPassword.getText().toString().trim();
            String password2 = edPassword2.getText().toString().trim();

            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || password2.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(password2)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            DbConnector.checkUsernameExists(username, exists -> {
                if (exists) {
                    Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show();
                } else {
                    DbConnector.insertUser(username, email, password, new DbConnector.InsertCallback() {
                        @Override
                        public void onSuccess() {
                            Toast.makeText(Registation.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Registation.this, Login.class));
                            finish();
                        }

                        @Override
                        public void onFailure(String errorMessage) {
                            Toast.makeText(Registation.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        });
    }
}
