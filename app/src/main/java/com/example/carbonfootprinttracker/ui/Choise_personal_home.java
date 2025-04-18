package com.example.carbonfootprinttracker.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.carbonfootprinttracker.R;
import com.example.carbonfootprinttracker.personal.Registation;

public class Choise_personal_home extends AppCompatActivity {

    Button personalButton, industryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_choise_personal_home);


            personalButton = findViewById(R.id.pesonal);
            industryButton = findViewById(R.id.industry);

            //button for personal--> when click here redirect to the login page
            personalButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Choise_personal_home.this, Registation.class));
                }
            });


        //button for indsutry--> when click here redirect to the login page
        industryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Choise_personal_home.this,Registation.class));
            }
        });

    }
}