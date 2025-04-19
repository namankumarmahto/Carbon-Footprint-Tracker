package com.example.carbonfootprinttracker.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.carbonfootprinttracker.R;
import com.example.carbonfootprinttracker.industry.Login_Industry;
import com.example.carbonfootprinttracker.industry.Registation_Industry;
import com.example.carbonfootprinttracker.personal.Login;
import com.example.carbonfootprinttracker.personal.Registation;

public class Choise_personal_home extends AppCompatActivity {

    Button personalButton, industryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choise_personal_home);

        personalButton = findViewById(R.id.pesonal);
        industryButton = findViewById(R.id.industry);

        personalButton.setOnClickListener(v -> startActivity(new Intent(this, Login.class)));
        industryButton.setOnClickListener(v -> startActivity(new Intent(this, Login_Industry.class)));
    }
}
