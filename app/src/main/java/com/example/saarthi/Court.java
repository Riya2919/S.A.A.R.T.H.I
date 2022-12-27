package com.example.saarthi;

public class Court {
    private String cnr;
    private String convictName;
    private String punishment;
    private String courtAddress;
    private String courtLevel;

    public Court() {
        // Default constructor required for calls to DataSnapshot.getValue(CourtOrder.class)
    }

    public Court(String cnr, String convictName, String punishment, String courtAddress, String courtLevel) {
        this.cnr = cnr;
        this.convictName = convictName;
        this.punishment = punishment;
        this.courtAddress = courtAddress;
        this.courtLevel = courtLevel;
    }

    public String getCnr() {
        return cnr;
    }

    public void setCnr(String cnr) {
        this.cnr = cnr;
    }

    public String getConvictName() {
        return convictName;
    }

    public void setConvictName(String convictName) {
        this.convictName = convictName;
    }

    public String getPunishment() {
        return punishment;
    }

    public void setPunishment(String punishment) {
        this.punishment = punishment;
    }

    public String getCourtAddress() {
        return courtAddress;
    }

    public void setCourtAddress(String courtAddress) {
        this.courtAddress = courtAddress;
    }

    public String getCourtLevel() {
        return courtLevel;
    }

    public void setCourtLevel(String courtLevel) {
        this.courtLevel = courtLevel;
    }
}

