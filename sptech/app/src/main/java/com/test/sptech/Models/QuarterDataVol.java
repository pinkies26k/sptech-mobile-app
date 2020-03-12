package com.test.sptech.Models;

import java.math.BigDecimal;

public class QuarterDataVol {

    private BigDecimal volumeOfMobileData;
    private String volumeOfMobileDataStr;
    private String quarter;
    private boolean decreaseInDataVol;

    public QuarterDataVol(String quarter, String volumeOfMobileData) {
        this.quarter = quarter;
        this.volumeOfMobileData = new BigDecimal(volumeOfMobileData);
        decreaseInDataVol = false;
    }

//    public QuarterDataVol(String quarter, String volumeOfMobileData, boolean decreaseInDataVol) {
//        this.quarter = quarter;
//        this.volumeOfMobileDataStr = volumeOfMobileData;
//        this.decreaseInDataVol = decreaseInDataVol;
//    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
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
