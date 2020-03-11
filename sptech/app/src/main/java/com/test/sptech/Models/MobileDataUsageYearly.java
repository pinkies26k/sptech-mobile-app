package com.test.sptech.Models;

public class MobileDataUsageYearly {

    private int year;
    private float volumeOfMobileData;
    private String yearStr;
    private String volumeOfMobileDataStr;

    public MobileDataUsageYearly(String volumeOfMobileData, String yearStr) {
        this.volumeOfMobileData = Float.parseFloat(volumeOfMobileData);
        this.yearStr = yearStr;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getVolumeOfMobileData() {
        return volumeOfMobileData;
    }

    public void setVolumeOfMobileData(float volumeOfMobileData) {
        this.volumeOfMobileData = volumeOfMobileData;
    }

    public String getYearStr() {
        //return String.valueOf(year);
        return yearStr;
    }

    public String getVolumeOfMobileDataStr() {
        return String.valueOf(volumeOfMobileData);
    }

}
