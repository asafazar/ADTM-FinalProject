package com.model.nextTradingDate;

import java.sql.Time;
import java.util.Date;

/**
 * Created by asaf on 10/02/2016.
 */
public class NextTradingDateDerivativeDetailsType3 {
    private String id;
    private int recordType;
    private int derivativeId;
    private int fluctuationCoefficient;
    private boolean isMultiplierInPriceCode;
    private int basePrice;
    private String derivativeSymbol;
    private Time tradingStartTime;
    private Time tradingEndTime;
    private int minimumOrderSize;
    private int maximumOrderSize;
    private int lotSize;
    private Date lastTradingDate;
    private boolean clearingMethodFlag;
    private int contractSize;
    private int expirationWeekNumber;
    private String filler;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRecordType() {
        return recordType;
    }

    public void setRecordType(int recordType) {
        this.recordType = recordType;
    }

    public int getDerivativeId() {
        return derivativeId;
    }

    public void setDerivativeId(int derivativeId) {
        this.derivativeId = derivativeId;
    }

    public int getFluctuationCoefficient() {
        return fluctuationCoefficient;
    }

    public void setFluctuationCoefficient(int fluctuationCoefficient) {
        this.fluctuationCoefficient = fluctuationCoefficient;
    }

    public boolean isMultiplierInPriceCode() {
        return isMultiplierInPriceCode;
    }

    public void setMultiplierInPriceCode(boolean multiplierInPriceCode) {
        isMultiplierInPriceCode = multiplierInPriceCode;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public String getDerivativeSymbol() {
        return derivativeSymbol;
    }

    public void setDerivativeSymbol(String derivativeSymbol) {
        this.derivativeSymbol = derivativeSymbol;
    }

    public Time getTradingStartTime() {
        return tradingStartTime;
    }

    public void setTradingStartTime(Time tradingStartTime) {
        this.tradingStartTime = tradingStartTime;
    }

    public Time getTradingEndTime() {
        return tradingEndTime;
    }

    public void setTradingEndTime(Time tradingEndTime) {
        this.tradingEndTime = tradingEndTime;
    }

    public int getMinimumOrderSize() {
        return minimumOrderSize;
    }

    public void setMinimumOrderSize(int minimumOrderSize) {
        this.minimumOrderSize = minimumOrderSize;
    }

    public int getMaximumOrderSize() {
        return maximumOrderSize;
    }

    public void setMaximumOrderSize(int maxmumOrderSize) {
        this.maximumOrderSize = maxmumOrderSize;
    }

    public int getLotSize() {
        return lotSize;
    }

    public void setLotSize(int lotSize) {
        this.lotSize = lotSize;
    }

    public Date getLastTradingDate() {
        return lastTradingDate;
    }

    public void setLastTradingDate(Date lastTradingDate) {
        this.lastTradingDate = lastTradingDate;
    }

    public boolean isClearingMethodFlag() {
        return clearingMethodFlag;
    }

    public void setClearingMethodFlag(boolean clearingMethodFlag) {
        this.clearingMethodFlag = clearingMethodFlag;
    }

    public int getContractSize() {
        return contractSize;
    }

    public void setContractSize(int contractSize) {
        this.contractSize = contractSize;
    }

    public int getExpirationWeekNumber() {
        return expirationWeekNumber;
    }

    public void setExpirationWeekNumber(int expirationWeekNumber) {
        this.expirationWeekNumber = expirationWeekNumber;
    }

    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }

}
