package com.chordncode.springmvcboard.data.util;

public enum ResultStatus {
    
    SUCCESS("SUCCESS"), 
    FAILED("FAILED"),
    NONE("NONE");

    private final String resultStatus;

    private ResultStatus(String resultStatus){
        this.resultStatus = resultStatus;
    }

    public String getStatus(){
        return resultStatus;
    }

}
