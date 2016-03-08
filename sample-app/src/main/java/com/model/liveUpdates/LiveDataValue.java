package com.model.liveUpdates;

/**
 * Created by asaf on 17/02/2016.
 */
public class LiveDataValue {
    String id;
    Double madadValue;
    Double callValue;
    Double putValue;

    public LiveDataValue() {
    }

    public LiveDataValue(Double madadValue, Double callValue, Double putValue) {
        this.madadValue = madadValue;
        this.callValue = callValue;
        this.putValue = putValue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getMadadValue() {
        return madadValue;
    }

    public void setMadadValue(Double madadValue) {
        this.madadValue = madadValue;
    }

    public Double getCallValue() {
        return callValue;
    }

    public void setCallValue(Double callValue) {
        this.callValue = callValue;
    }

    public Double getPutValue() {
        return putValue;
    }

    public void setPutValue(Double putValue) {
        this.putValue = putValue;
    }
}
