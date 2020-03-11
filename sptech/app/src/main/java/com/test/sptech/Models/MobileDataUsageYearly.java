package com.test.sptech.Models;

import java.math.BigDecimal;

public class MobileDataUsageYearly {

    private int year;
    private BigDecimal volumeOfMobileData;
    private String yearStr;
    private String volumeOfMobileDataStr;

    public MobileDataUsageYearly(BigDecimal volumeOfMobileData, String yearStr) {
        this.volumeOfMobileData = volumeOfMobileData;
        this.yearStr = yearStr;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public BigDecimal getVolumeOfMobileData() {
        return volumeOfMobileData;
    }

    public void setVolumeOfMobileData(BigDecimal volumeOfMobileData) {
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
