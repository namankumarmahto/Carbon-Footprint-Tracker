package com.example.carbonfootprinttracker.personal;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import com.example.carbonfootprinttracker.R;
import com.github.mikephil.charting.charts.BarChart;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

public class Transpotation extends AppCompatActivity {

    Spinner vehicleSpinner, fuelSpinner;
    EditText distanceInput;
    Button addTripButton;
    TextView tripLog;
    BarChart barChart;

    ArrayList<BarEntry> entries = new ArrayList<>();
    ArrayList<String> labels = new ArrayList<>();
    int tripCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transpotation);

        vehicleSpinner = findViewById(R.id.vehicleSpinner);
        fuelSpinner = findViewById(R.id.fuelSpinner);
        distanceInput = findViewById(R.id.distanceInput);
        addTripButton = findViewById(R.id.addTripButton);
        tripLog = findViewById(R.id.tripLog);
        barChart = findViewById(R.id.barChart);

        // Optional: Set sample values for testing
        ArrayAdapter<CharSequence> vehicleAdapter = ArrayAdapter.createFromResource(
                this, R.array.vehicles, android.R.layout.simple_spinner_item);
        vehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vehicleSpinner.setAdapter(vehicleAdapter);

        ArrayAdapter<CharSequence> fuelAdapter = ArrayAdapter.createFromResource(
                this, R.array.fuels, android.R.layout.simple_spinner_item);
        fuelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fuelSpinner.setAdapter(fuelAdapter);

        addTripButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vehicle = vehicleSpinner.getSelectedItem().toString();
                String fuel = fuelSpinner.getSelectedItem().toString();
                String distanceStr = distanceInput.getText().toString().trim();

                if (distanceStr.isEmpty()) {
                    distanceInput.setError("Enter distance");
                    return;
                }

                float distance = Float.parseFloat(distanceStr);
                float emission = calculateEmission(vehicle, fuel, distance);

                tripCount++;
                entries.add(new BarEntry(tripCount, emission));
                labels.add("Trip " + tripCount);
                tripLog.append("\n• " + vehicle + " | " + fuel + " | " + distance + " km");

                updateChart();
                distanceInput.setText(""); // Clear input
            }
        });
    }

    private float calculateEmission(String vehicle, String fuel, float distance) {
        float emissionFactor = 0.0f;

        if (vehicle.equals("Car")) {
            if (fuel.equals("Petrol")) emissionFactor = 0.192f;
            else if (fuel.equals("Diesel")) emissionFactor = 0.171f;
        } else if (vehicle.equals("Bus")) {
            emissionFactor = 0.089f;
        } else if (vehicle.equals("Bike")) {
            emissionFactor = 0.072f;
        }

        return distance * emissionFactor;
    }

    private void updateChart() {
        BarDataSet dataSet = new BarDataSet(entries, "CO₂ kg");
        dataSet.setColor(getResources().getColor(R.color.teal_700));
        BarData data = new BarData(dataSet);
        data.setBarWidth(0.9f);
        barChart.setData(data);
        barChart.setFitBars(true);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        barChart.invalidate(); // Refresh chart
    }
}
