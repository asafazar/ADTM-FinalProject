package com.model.nextTradingDate;

import java.util.Date;

/**
 * Created by asaf on 10/02/2016.
 */
public class NextTradingDateDerivativeDetailsType2 {
    private String id;
    private int recordType;
    private int derivativeId;
    private int derivativeType;
    private Date expirationDate;
    private int strikePrice;
    private int underlyingAssetCode;
    private int underlyingAssetMultiplier;
    private int underlyingAssetType;
    private boolean adjustedDerivativeFlag;
    private int upperDailyFluctuation;
    private int lowerDailyFluctuation;
    private String derivativeHebrewName;
    private boolean shortTermDerviativeFlag;
    private boolean newDerviativeFlag;
    private String filler;
    private int marketId;
    private boolean derivativeIssuedDuringTradeFlag;

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

    public int getDerivativeType() {
        return derivativeType;
    }

    public void setDerivativeType(int derivativeType) {
        this.derivativeType = derivativeType;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getStrikePrice() {
        return strikePrice;
    }

    public void setStrikePrice(int strikePrice) {
        this.strikePrice = strikePrice;
    }

    public int getUnderlyingAssetCode() {
        return underlyingAssetCode;
    }

    public void setUnderlyingAssetCode(int underlyingAssetCode) {
        this.underlyingAssetCode = underlyingAssetCode;
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

    public boolean isAdjustedDerivativeFlag() {
        return adjustedDerivativeFlag;
    }

    public void setAdjustedDerivativeFlag(boolean adjustedDerivativeFlag) {
        this.adjustedDerivativeFlag = adjustedDerivativeFlag;
    }

    public int getUpperDailyFluctuation() {
        return upperDailyFluctuation;
    }

    public void setUpperDailyFluctuation(int upperDailyFluctuation) {
        this.upperDailyFluctuation = upperDailyFluctuation;
    }

    public int getLowerDailyFluctuation() {
        return lowerDailyFluctuation;
    }

    public void setLowerDailyFluctuation(int lowerDailyFluctuation) {
        this.lowerDailyFluctuation = lowerDailyFluctuation;
    }

    public String getDerivativeHebrewName() {
        return derivativeHebrewName;
    }

    public void setDerivativeHebrewName(String derivativeHebrewName) {
        this.derivativeHebrewName = derivativeHebrewName;
    }

    public boolean isShortTermDerviativeFlag() {
        return shortTermDerviativeFlag;
    }

    public void setShortTermDerviativeFlag(boolean shortTermDerviativeFlag) {
        this.shortTermDerviativeFlag = shortTermDerviativeFlag;
    }

    public boolean isNewDerviativeFlag() {
        return newDerviativeFlag;
    }

    public void setNewDerviativeFlag(boolean newDerviativeFlag) {
        this.newDerviativeFlag = newDerviativeFlag;
    }

    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }

    public int getMarketId() {
        return marketId;
    }

    public void setMarketId(int marketId) {
        this.marketId = marketId;
    }

    public boolean isDerivativeIssuedDuringTradeFlag() {
        return derivativeIssuedDuringTradeFlag;
    }

    public void setDerivativeIssuedDuringTradeFlag(boolean derivativeIssuedDuringTradeFlag) {
        this.derivativeIssuedDuringTradeFlag = derivativeIssuedDuringTradeFlag;
    }
}
