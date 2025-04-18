package com.example.carbonfootprinttracker.personal;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.carbonfootprinttracker.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Home extends Fragment {
    Button transportationButton, electricityButton, foodButton, otherButton;

    private PieChart donutChart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home, container, false);

        donutChart = view.findViewById(R.id.donutChart);
        transportationButton = view.findViewById(R.id.transpotaion);
        electricityButton = view.findViewById(R.id.electricity); // Ensure IDs are correct
//        foodButton = view.findViewById(R.id.food);
//        otherButton = view.findViewById(R.id.other);

        setupDonutChart();

        // Set onClickListeners for the buttons
        transportationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Transpotation.class)); // Ensure this Activity exists
            }
        });

//        electricityButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getActivity(), ElectricityActivity.class)); // Ensure this Activity exists
//            }
//        });
//
//        foodButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getActivity(), FoodActivity.class)); // Ensure this Activity exists
//            }
//        });
//
//        otherButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getActivity(), OtherActivity.class)); // Ensure this Activity exists
//            }
//        });

        return view; // This should be the last statement in onCreateView
    }

    private void setupDonutChart() {
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(35f, "Transportation"));
        entries.add(new PieEntry(25f, "Electricity"));
        entries.add(new PieEntry(20f, "Food"));
        entries.add(new PieEntry(20f, "Other"));

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(14f);
        data.setValueTextColor(android.graphics.Color.WHITE);

        donutChart.setData(data);
        donutChart.setUsePercentValues(true);
        donutChart.setDrawHoleEnabled(true);
        donutChart.setHoleRadius(40f);
        donutChart.setTransparentCircleRadius(45f);
        donutChart.setEntryLabelTextSize(12f);
        donutChart.setEntryLabelColor(android.graphics.Color.BLACK);
        donutChart.getDescription().setEnabled(false);

        Legend legend = donutChart.getLegend();
        legend.setEnabled(false);

        donutChart.invalidate(); // refresh
    }
}
