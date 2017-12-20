package com.codechallenge.badrobot.bitcoinstats.utils;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateValueFormatter implements IAxisValueFormatter {

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        long dv = (long) ((value)*1000);
        Date df = new java.util.Date(dv);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy", Locale.US);
        return String.valueOf(simpleDateFormat.format(df));
    }
}
