package com.example.carbonfootprinttracker.industry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.carbonfootprinttracker.R;
import com.example.carbonfootprinttracker.db.industry.DbIndustryConnector;

public class Registation_Industry extends AppCompatActivity {

    EditText edCompanyName, edCompnyType, edGstNo, edEmail, edPhoneNo, edUsername, edPassword, edPassword2;
    Button registorButton;
    TextView loginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registation_industry);

        edCompanyName = findViewById(R.id.company_name);
        edCompnyType = findViewById(R.id.type_of_industry);
        edGstNo = findViewById(R.id.gst_no);
        edEmail = findViewById(R.id.email);
        edPhoneNo = findViewById(R.id.Phone_no);
        edUsername = findViewById(R.id.username);
        edPassword = findViewById(R.id.password);
        edPassword2 = findViewById(R.id.password2);
        registorButton = findViewById(R.id.registor);
        loginText = findViewById(R.id.login_industry);

        loginText.setOnClickListener(view -> {
            startActivity(new Intent(Registation_Industry.this, Login_Industry.class));
            finish();
        });

        registorButton.setOnClickListener(view -> {
            String companyName = edCompanyName.getText().toString().trim();
            String companyType = edCompnyType.getText().toString().trim();
            String gstNo = edGstNo.getText().toString().trim();
            String email = edEmail.getText().toString().trim();
            String phoneNo = edPhoneNo.getText().toString().trim();
            String username = edUsername.getText().toString().trim();
            String password = edPassword.getText().toString().trim();
            String password2 = edPassword2.getText().toString().trim();

            if (companyName.isEmpty() || companyType.isEmpty() || gstNo.isEmpty() ||
                    email.isEmpty() || phoneNo.isEmpty() || username.isEmpty() ||
                    password.isEmpty() || password2.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(password2)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            DbIndustryConnector.checkUsernameExists(username, exists -> {
                if (exists) {
                    Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show();
                } else {
                    DbIndustryConnector.insertUser(
                            username, email, password, companyName, companyType, gstNo, phoneNo,
                            new DbIndustryConnector.InsertCallback() {
                                @Override
                                public void onSuccess() {
                                    Toast.makeText(Registation_Industry.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Registation_Industry.this, Login_Industry.class));
                                    finish();
                                }

                                @Override
                                public void onFailure(String errorMessage) {
                                    Toast.makeText(Registation_Industry.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            });
        });
    }
}
