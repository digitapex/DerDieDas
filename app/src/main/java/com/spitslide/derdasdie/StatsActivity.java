package com.spitslide.derdasdie;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class StatsActivity extends ThemedActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        setupChart();
    }

    private void setupChart() {
        LineChart chart = findViewById(R.id.chart);
        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(1, 1));
        LineDataSet dataSet = new LineDataSet(entries, "");
        dataSet.setDrawCircles(false);

        LineData lineData = new LineData(dataSet);
        lineData.setDrawValues(false);
        chart.setData(lineData);

        chart.getAxisRight().setEnabled(false);

        chart.getAxisLeft().setDrawGridLines(true);
        chart.getAxisLeft().setAxisMinimum(0);

        // prevents from showing 105 %
        chart.getAxisLeft().setSpaceTop(5);
        chart.getAxisLeft().setDrawZeroLine(true);
        chart.getAxisLeft().setLabelCount(20);
        chart.getAxisLeft().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(1);
                return df.format(value) + " %";
            }
        });

        chart.getXAxis().setDrawGridLines(false);
        chart.getXAxis().setDrawAxisLine(true);
        chart.getXAxis().setDrawLabels(false);
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);

        chart.setDescription(null);
        chart.getLegend().setEnabled(false);
    }
}
