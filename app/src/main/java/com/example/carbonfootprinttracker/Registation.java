package com.example.carbonfootprinttracker;

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
                    Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}