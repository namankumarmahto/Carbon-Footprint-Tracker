package com.example.carbonfootprinttracker.industry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.carbonfootprinttracker.R;

public class Dashboard_Industry extends AppCompatActivity {

    private View btnEmissionScope, btnOperational, btnLogistics, btnLifecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_industry);

        btnEmissionScope = findViewById(R.id.btn_emission_scope);
        btnOperational = findViewById(R.id.btn_operational);
        btnLogistics = findViewById(R.id.btn_logistics);
        btnLifecycle = findViewById(R.id.btn_lifecycle);

        setButtonData(btnEmissionScope, R.drawable.emission_icon, "Emission Scope");
        setButtonData(btnOperational, R.drawable.operisonal_icon, "Operational Parameters");
        setButtonData(btnLogistics, R.drawable.logistics_icon, "Supply Chain and Logistics");
        setButtonData(btnLifecycle, R.drawable.lifecycle_icon, "Product Lifecycle");

        btnEmissionScope.setOnClickListener(v -> openSection("EmissionScopeActivity"));
        btnOperational.setOnClickListener(v -> openSection("OperationalParametersActivity"));
        btnLogistics.setOnClickListener(v -> openSection("SupplyChainActivity"));
        btnLifecycle.setOnClickListener(v -> openSection("ProductLifecycleActivity"));
    }

    private void setButtonData(View view, int iconRes, String title) {
        ImageView icon = view.findViewById(R.id.icon);
        TextView label = view.findViewById(R.id.title);
        icon.setImageResource(iconRes);
        label.setText(title);
    }

    private void openSection(String activityName) {
        try {
            Class<?> activityClass = Class.forName("com.example.carbonfootprinttracker.industry." + activityName);
            startActivity(new Intent(this, activityClass));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "Activity not found", Toast.LENGTH_SHORT).show();
        }
    }
}
