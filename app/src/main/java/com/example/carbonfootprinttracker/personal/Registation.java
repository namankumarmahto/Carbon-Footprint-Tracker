package com.example.carbonfootprinttracker.personal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.carbonfootprinttracker.R;
import com.example.carbonfootprinttracker.db.DbConnector;

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

        registorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUsername.getText().toString().trim();
                String email = edEmail.getText().toString().trim();
                String password = edPassword.getText().toString().trim();
                String password2 = edPassword2.getText().toString().trim();

                if (username.isEmpty() || email.isEmpty() || password.isEmpty() || password2.isEmpty()) {
                    Toast.makeText(Registation.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(password2)) {
                    Toast.makeText(Registation.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                    return;
                }

                DbConnector.insertUser(username, email, password, new DbConnector.InsertCallback() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(Registation.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        // Navigate to login screen if needed
                        Intent intent = new Intent(Registation.this, Login.class);
                        startActivity(intent);
                        finish(); // finish registration so user can't come back with back button

                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        Toast.makeText(Registation.this, "Registration Failed: " + errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
