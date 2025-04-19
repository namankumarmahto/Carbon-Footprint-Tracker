package com.example.carbonfootprinttracker.personal;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.carbonfootprinttracker.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.*;

import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.*;

public class Food_personal extends AppCompatActivity {

    Spinner foodSpinner;
    EditText quantityInput;
    Button calcFoodBtn;
    TextView foodResult;
    BarChart foodBarChart;

    Map<String, Float> emissionFactors;
    ArrayList<BarEntry> entries = new ArrayList<>();
    ArrayList<String> labels = new ArrayList<>();
    int recordCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_personal);

        foodSpinner = findViewById(R.id.foodSpinner);
        quantityInput = findViewById(R.id.quantityInput);
        calcFoodBtn = findViewById(R.id.calcFoodBtn);
        foodResult = findViewById(R.id.foodResult);
        foodBarChart = findViewById(R.id.foodBarChart);

        setupEmissionFactors();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>(emissionFactors.keySet()));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foodSpinner.setAdapter(adapter);

        calcFoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedFood = foodSpinner.getSelectedItem().toString();
                String quantityStr = quantityInput.getText().toString().trim();

                if (quantityStr.isEmpty()) {
                    quantityInput.setError("Enter quantity in kg");
                    return;
                }

                float quantity = Float.parseFloat(quantityStr);
                float factor = emissionFactors.get(selectedFood);
                float emission = quantity * factor;

                foodResult.setText("Estimated Emission: " + String.format("%.2f", emission) + " kg CO₂");

                recordCount++;
                entries.add(new BarEntry(recordCount, emission));
                labels.add(selectedFood + " " + recordCount);
                updateChart();

                quantityInput.setText("");
            }
        });
    }

    private void setupEmissionFactors() {
        emissionFactors = new HashMap<>();
        emissionFactors.put("Beef", 27.0f);
        emissionFactors.put("Lamb", 39.2f);
        emissionFactors.put("Pork", 12.1f);
        emissionFactors.put("Chicken", 6.9f);
        emissionFactors.put("Turkey", 10.9f);
        emissionFactors.put("Fish", 6.1f);
        emissionFactors.put("Eggs", 4.8f);
        emissionFactors.put("Cheese", 13.5f);
        emissionFactors.put("Milk", 1.9f);
        emissionFactors.put("Butter", 11.9f);
        emissionFactors.put("Yogurt", 2.2f);
        emissionFactors.put("Tofu", 3.0f);
        emissionFactors.put("Lentils", 0.9f);
        emissionFactors.put("Beans", 1.2f);
        emissionFactors.put("Rice", 2.7f);
        emissionFactors.put("Wheat", 1.4f);
        emissionFactors.put("Corn", 1.1f);
        emissionFactors.put("Potatoes", 0.3f);
        emissionFactors.put("Tomatoes", 1.1f);
        emissionFactors.put("Broccoli", 0.5f);
        emissionFactors.put("Carrots", 0.4f);
        emissionFactors.put("Spinach", 0.9f);
        emissionFactors.put("Apples", 0.4f);
        emissionFactors.put("Bananas", 0.7f);
        emissionFactors.put("Berries", 1.5f);
        emissionFactors.put("Oranges", 0.3f);
        emissionFactors.put("Avocados", 2.2f);
        emissionFactors.put("Nuts", 2.3f);
        emissionFactors.put("Olive Oil", 6.0f);
    }

    private void updateChart() {
        BarDataSet dataSet = new BarDataSet(entries, "Food CO₂ (kg)");
        dataSet.setColors(getResources().getColor(R.color.teal_700));
        BarData data = new BarData(dataSet);
        data.setBarWidth(0.8f);

        foodBarChart.setData(data);
        foodBarChart.setFitBars(true);

        XAxis xAxis = foodBarChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        foodBarChart.invalidate();
    }
}
