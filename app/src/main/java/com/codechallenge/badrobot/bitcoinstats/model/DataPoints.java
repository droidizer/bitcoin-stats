package com.codechallenge.badrobot.bitcoinstats.model;


import com.google.gson.annotations.SerializedName;

public class DataPoints {

    @SerializedName("x")
    public long xPoint;

    @SerializedName("y")
    public double yPoint;

    public long getxPoint() {
        return xPoint;
    }

    public void setxPoint(long xPoint) {
        this.xPoint = xPoint;
    }

    public double getyPoint() {
        return yPoint;
    }

    public void setyPoint(double yPoint) {
        this.yPoint = yPoint;
    }
}
