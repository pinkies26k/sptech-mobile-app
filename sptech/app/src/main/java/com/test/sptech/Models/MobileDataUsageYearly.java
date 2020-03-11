package com.test.sptech.Models;

import com.test.sptech.Constant;

import java.math.BigDecimal;
import java.util.List;

public class MobileDataUsageYearly {

    private int year;
    private BigDecimal volumeOfMobileData;
    private String yearStr;
    private String volumeOfMobileDataStr;
    private List<QuarterDataVol> quarterList;
    //private boolean decreaseInDataVol;

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

    public List<QuarterDataVol> getQuarterList() {
        return quarterList;
    }

    public void setQuarterList(List<QuarterDataVol> quarterList) {
        this.quarterList = quarterList;
    }

    public boolean hasDecreaseInDataVol() {

        BigDecimal prevVolData = BigDecimal.ZERO;
        for(QuarterDataVol q: quarterList){
            if(prevVolData.equals(BigDecimal.ZERO)){
                prevVolData = q.getVolumeOfMobileData();
            }else if(prevVolData.compareTo(q.getVolumeOfMobileData()) == Constant.IS_GREATER_THAN){
                return true;
            }
        }

        return false;
    }
}
