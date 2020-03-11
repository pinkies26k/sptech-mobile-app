package com.test.sptech.Models;

import java.math.BigDecimal;

public class QuarterDataVol {

    private BigDecimal volumeOfMobileData;
    private String volumeOfMobileDataStr;
    private int quarter;
    private boolean decreaseInDataVol;

    public int getQuarter() {
        return quarter;
    }

    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }

    public BigDecimal getVolumeOfMobileData() {
        return volumeOfMobileData;
    }

    public void setVolumeOfMobileData(BigDecimal volumeOfMobileData) {
        this.volumeOfMobileData = volumeOfMobileData;
    }

    public String getVolumeOfMobileDataStr() {
        return String.valueOf(volumeOfMobileData);
    }

    public boolean hasDecreaseInDataVol() {
        return decreaseInDataVol;
    }

    public void setDecreaseInDataVolFlag(boolean decreaseInDataVol) {
        this.decreaseInDataVol = decreaseInDataVol;
    }
}
