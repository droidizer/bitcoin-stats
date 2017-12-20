package com.codechallenge.badrobot.bitcoinstats.utils;

import android.databinding.BindingAdapter;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;

public class LineChartBindings {

    @SuppressWarnings("unchecked")
    @BindingAdapter("dataPoints")
    public static <T> void setDataPoints(LineChart linechart, LineData items) {
        if (linechart != null) {
            linechart.setData(items);
            linechart.invalidate();
        }
    }
}
