package com.model.nextTradingDate;

import java.util.Date;

/**
 * Created by asaf on 10/02/2016.
 */
public class NextTradingDateDerivativeDetailsType5 {
    private String id;
    private int recordType;
    private int derivativeId;
    private Date exactExpirationDate;
    private Date settlementPriceDate;
    private String isin;
    private int underlyingAssetMultiplier;
    private int underlyingAssetType;
    private int haltReasonCode;
    private int openPositionsLimit;
    private int adjustmentNumber;
    private boolean callFlag;
    private int discountedCoupon;
    private int maximumPrearrangedOrderSize;
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

    public Date getExactExpirationDate() {
        return exactExpirationDate;
    }

    public void setExactExpirationDate(Date exactExpirationDate) {
        this.exactExpirationDate = exactExpirationDate;
    }

    public Date getSettlementPriceDate() {
        return settlementPriceDate;
    }

    public void setSettlementPriceDate(Date settlementPriceDate) {
        this.settlementPriceDate = settlementPriceDate;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public int getUnderlyingAssetMultiplier() {
        return underlyingAssetMultiplier;
    }

    public void setUnderlyingAssetMultiplier(int underlyingAssetMultiplier) {
        this.underlyingAssetMultiplier = underlyingAssetMultiplier;
    }

    public int getUnderlyingAssetType() {
        return underlyingAssetType;
    }

    public void setUnderlyingAssetType(int underlyingAssetType) {
        this.underlyingAssetType = underlyingAssetType;
    }

    public int getHaltReasonCode() {
        return haltReasonCode;
    }

    public void setHaltReasonCode(int haltReasonCode) {
        this.haltReasonCode = haltReasonCode;
    }

    public int getOpenPositionsLimit() {
        return openPositionsLimit;
    }

    public void setOpenPositionsLimit(int openPositionsLimit) {
        this.openPositionsLimit = openPositionsLimit;
    }

    public int getAdjustmentNumber() {
        return adjustmentNumber;
    }

    public void setAdjustmentNumber(int adjustmentNumber) {
        this.adjustmentNumber = adjustmentNumber;
    }

    public boolean isCallFlag() {
        return callFlag;
    }

    public void setCallFlag(boolean callFlag) {
        this.callFlag = callFlag;
    }

    public int getDiscountedCoupon() {
        return discountedCoupon;
    }

    public void setDiscountedCoupon(int discountedCoupon) {
        this.discountedCoupon = discountedCoupon;
    }

    public int getMaximumPrearrangedOrderSize() {
        return maximumPrearrangedOrderSize;
    }

    public void setMaximumPrearrangedOrderSize(int maximumPrearrangedOrderSize) {
        this.maximumPrearrangedOrderSize = maximumPrearrangedOrderSize;
    }

    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }

}

