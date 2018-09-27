package com.spitslide.derdasdie;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class StatsActivity extends ThemedActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        try {
            setupChart();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void setupChart() throws UnsupportedEncodingException {
        int nounsCount = FileUtils.getNounsCount(this);
        LineChart chart = findViewById(R.id.chart);
        List<Entry> entries = new ArrayList<>();
        List<Float> scores = new DatabaseUtil(this).getAllScores();
        // TODO - if 0 entries
        for (int i = 0; i < scores.size(); i++) {
            float percent = scores.get(i) / nounsCount;
            entries.add(new Entry(i, percent));
        }


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
        chart.getAxisLeft().setTextColor(ThemeUtil.getNormalButtonTxtColorAttr(this));
        chart.getAxisLeft().setGridColor(Color.RED);
        chart.getAxisLeft().setDrawZeroLine(true);
        chart.getAxisLeft().setLabelCount(20);
        chart.getAxisLeft().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(4);
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
