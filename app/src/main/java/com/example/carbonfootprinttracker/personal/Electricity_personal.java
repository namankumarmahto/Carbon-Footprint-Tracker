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

public class Electricity_personal extends AppCompatActivity {

    EditText electricityInput;
    Button calcElectricityBtn;
    TextView electricityResult;
    BarChart electricityBarChart;

    ArrayList<BarEntry> entries = new ArrayList<>();
    ArrayList<String> labels = new ArrayList<>();
    int recordCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricity_personal);

        electricityInput = findViewById(R.id.electricityInput);
        calcElectricityBtn = findViewById(R.id.calcElectricityBtn);
        electricityResult = findViewById(R.id.electricityResult);
        electricityBarChart = findViewById(R.id.electricityBarChart);

        calcElectricityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String elecStr = electricityInput.getText().toString().trim();

                if (elecStr.isEmpty()) {
                    electricityInput.setError("Enter electricity usage in kWh");
                    return;
                }

                float kWh = Float.parseFloat(elecStr);
                float emission = kWh * 0.475f; // Average emission factor

                String result = "Estimated Emission: " + String.format("%.2f", emission) + " kg CO₂";
                electricityResult.setText(result);

                recordCount++;
                entries.add(new BarEntry(recordCount, emission));
                labels.add("Usage " + recordCount);
                updateChart();

                electricityInput.setText(""); // clear input
            }
        });
    }

    private void updateChart() {
        BarDataSet dataSet = new BarDataSet(entries, "Electricity CO₂ (kg)");
        dataSet.setColors(getResources().getColor(R.color.dark_blue), getResources().getColor(R.color.gray));  // Corporate-friendly colors
        BarData data = new BarData(dataSet);
        data.setBarWidth(0.8f);

        electricityBarChart.setData(data);
        electricityBarChart.setFitBars(true);

        XAxis xAxis = electricityBarChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        electricityBarChart.invalidate(); // Refresh chart
    }
}
