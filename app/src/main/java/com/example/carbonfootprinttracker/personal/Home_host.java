package com.example.carbonfootprinttracker.personal;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.carbonfootprinttracker.R;

public class Home_host extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_host);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new Home())
                .commit();
    }
}
