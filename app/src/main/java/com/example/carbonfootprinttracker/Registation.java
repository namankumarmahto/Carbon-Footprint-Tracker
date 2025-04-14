package com.example.carbonfootprinttracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Registation extends AppCompatActivity {

    EditText edUsername, edEmail, edPassword, edPassword2;
    Button registorButton;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registation);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edUsername = findViewById(R.id.registation_username);
        edEmail = findViewById(R.id.registation_email);
        edPassword = findViewById(R.id.registation_password);
        edPassword2 = findViewById(R.id.registation_password2);
        registorButton = findViewById(R.id.registation_button);
        login = findViewById(R.id.registation_login);

        registorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUsername.getText().toString();
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                String password2 = edPassword2.getText().toString();

                if(username.length()==0 || email.length()==0 || password.length()==0 || password2.length()==0){
                    Toast.makeText(getApplicationContext(), "Fill All Details", Toast.LENGTH_SHORT).show();
                }else {
                    if(password.compareTo(password2)==0){
                        if(isValid(password)){
                            Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Registation.this,Login.class));
                        }else{
                            Toast.makeText(getApplicationContext(), "Password must be at least 8 characters long and include an uppercase letter, a lowercase letter, a number, and a special character. Spaces are not allowed.", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Registation.this,Login.class));
            }
        });
    }
    public static boolean isValid(String passwordhere) {

        int f1 = 0, f2 = 0, f3 = 0;

        if (passwordhere.length() < 8) {
            return false;
        } else {

            for (int p = 0; p < passwordhere.length(); p++) {
                if (Character.isLetter(passwordhere.charAt(p))) {
                    f1 = 1;
                }
            }

            for (int r = 0; r < passwordhere.length(); r++) {
                if (Character.isDigit(passwordhere.charAt(r))) {
                    f2 = 1;
                }
            }

            for (int s = 0; s < passwordhere.length(); s++) {
                char c = passwordhere.charAt(s);
                if ((c >= 33 && c <= 46) || c == 64) {
                    f3 = 1;
                }
            }

            if (f1 == 1 && f2 == 1 && f3 == 1)
                return true;
        }

        return false;
    }



}