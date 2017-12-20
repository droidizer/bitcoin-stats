package com.codechallenge.badrobot.bitcoinstats.utils;

import android.databinding.BindingAdapter;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

public class LineChartBindings {

    @SuppressWarnings("unchecked")
    @BindingAdapter("dataPoints")
    public static void setDataPoints(LineChart linechart, LineData items) {
        if (linechart != null) {
            linechart.setData(items);
            linechart.invalidate();
        }
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter("axisFormatter")
    public static void setXAxisFormatter(LineChart linechart, int i) {
        if (linechart != null) {
            IAxisValueFormatter xAxisFormatter = new DateValueFormatter();
            XAxis xAxis = linechart.getXAxis();
            xAxis.setValueFormatter(xAxisFormatter);
            linechart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
            linechart.invalidate();
        }
    }
}
