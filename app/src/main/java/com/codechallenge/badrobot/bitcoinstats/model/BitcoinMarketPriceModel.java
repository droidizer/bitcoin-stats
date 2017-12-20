package com.codechallenge.badrobot.bitcoinstats.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BitcoinMarketPriceModel {

    @SerializedName("status")
    public String status;

    @SerializedName("name")
    public String name;

    @SerializedName("unit")
    public String unit;

    @SerializedName("period")
    public String period;

    @SerializedName("description")
    public String description;

    @SerializedName("values")
    public List<DataPoints> datapoints;

    public String getStatus() {
        return status;
    }

    public BitcoinMarketPriceModel setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getName() {
        return name;
    }

    public BitcoinMarketPriceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public BitcoinMarketPriceModel setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public String getPeriod() {
        return period;
    }

    public BitcoinMarketPriceModel setPeriod(String period) {
        this.period = period;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BitcoinMarketPriceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<DataPoints> getDatapoints() {
        return datapoints;
    }

    public BitcoinMarketPriceModel setDatapoints(List<DataPoints> datapoints) {
        this.datapoints = datapoints;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BitcoinMarketPriceModel that = (BitcoinMarketPriceModel) o;

        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (unit != null ? !unit.equals(that.unit) : that.unit != null) return false;
        if (period != null ? !period.equals(that.period) : that.period != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;
        return datapoints != null ? datapoints.equals(that.datapoints) : that.datapoints == null;
    }

    @Override
    public int hashCode() {
        int result = status != null ? status.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        result = 31 * result + (period != null ? period.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (datapoints != null ? datapoints.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BitcoinMarketPriceModel{" +
                "status='" + status + '\'' +
                ", name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", period='" + period + '\'' +
                ", description='" + description + '\'' +
                ", datapoints=" + datapoints +
                '}';
    }
}
