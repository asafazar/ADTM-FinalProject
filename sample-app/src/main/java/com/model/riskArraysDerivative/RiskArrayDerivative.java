package com.model.riskArraysDerivative;

import java.util.Date;

/**
 * Created by asaf on 09/02/2016.
 */
public class RiskArrayDerivative {
    private String id;
    private int recordType;
    private int derivativeId;
    private int underlyingAssetCode;
    private Date expirationDate;
    private int strikePrice;
    private int derivativeType;
    private int riskFreeInterestRateRisk;
    private int underlyingAssetStandardDeviationPercent;
    private int extremeScenarioFactorForIndexDecline;
    private int riskArrayNumber;
    private int underlyingAssetInterestRate;
    private int derivativeMargins;
    private int derivativeMarginsForSpreadStrategy;
    private int numberOfDaysForSpreadStrategy;
    private int derivativeIncludedInSpreadStrategyFlag;
    private int extremeScenarioFactorForIndexRise;
    private Double delta;
    private int deltaSign;
    private int priceScanRangePercent;
    private int standardDeviationFluctuation;
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

    public int getUnderlyingAssetCode() {
        return underlyingAssetCode;
    }

    public void setUnderlyingAssetCode(int underlyingAssetCode) {
        this.underlyingAssetCode = underlyingAssetCode;
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

    public int getDerivativeType() {
        return derivativeType;
    }

    public void setDerivativeType(int derivativeType) {
        this.derivativeType = derivativeType;
    }

    public int getRiskFreeInterestRateRisk() {
        return riskFreeInterestRateRisk;
    }

    public void setRiskFreeInterestRateRisk(int riskFreeInterestRateRisk) {
        this.riskFreeInterestRateRisk = riskFreeInterestRateRisk;
    }

    public int getUnderlyingAssetStandardDeviationPercent() {
        return underlyingAssetStandardDeviationPercent;
    }

    public void setUnderlyingAssetStandardDeviationPercent(int underlyingAssetStandardDeviationPercent) {
        this.underlyingAssetStandardDeviationPercent = underlyingAssetStandardDeviationPercent;
    }

    public int getExtremeScenarioFactorForIndexDecline() {
        return extremeScenarioFactorForIndexDecline;
    }

    public void setExtremeScenarioFactorForIndexDecline(int extremeScenarioFactorForIndexDecline) {
        this.extremeScenarioFactorForIndexDecline = extremeScenarioFactorForIndexDecline;
    }

    public int getRiskArrayNumber() {
        return riskArrayNumber;
    }

    public void setRiskArrayNumber(int riskArrayNumber) {
        this.riskArrayNumber = riskArrayNumber;
    }

    public int getUnderlyingAssetInterestRate() {
        return underlyingAssetInterestRate;
    }

    public void setUnderlyingAssetInterestRate(int underlyingAssetInterestRate) {
        this.underlyingAssetInterestRate = underlyingAssetInterestRate;
    }

    public int getDerivativeMargins() {
        return derivativeMargins;
    }

    public void setDerivativeMargins(int derivativeMargins) {
        this.derivativeMargins = derivativeMargins;
    }

    public int getDerivativeMarginsForSpreadStrategy() {
        return derivativeMarginsForSpreadStrategy;
    }

    public void setDerivativeMarginsForSpreadStrategy(int derivativeMarginsForSpreadStrategy) {
        this.derivativeMarginsForSpreadStrategy = derivativeMarginsForSpreadStrategy;
    }

    public int getNumberOfDaysForSpreadStrategy() {
        return numberOfDaysForSpreadStrategy;
    }

    public void setNumberOfDaysForSpreadStrategy(int numberOfDaysForSpreadStrategy) {
        this.numberOfDaysForSpreadStrategy = numberOfDaysForSpreadStrategy;
    }

    public int getDerivativeIncludedInSpreadStrategyFlag() {
        return derivativeIncludedInSpreadStrategyFlag;
    }

    public void setDerivativeIncludedInSpreadStrategyFlag(int derivativeIncludedInSpreadStrategyFlag) {
        this.derivativeIncludedInSpreadStrategyFlag = derivativeIncludedInSpreadStrategyFlag;
    }

    public int getExtremeScenarioFactorForIndexRise() {
        return extremeScenarioFactorForIndexRise;
    }

    public void setExtremeScenarioFactorForIndexRise(int extremeScenarioFactorForIndexRise) {
        this.extremeScenarioFactorForIndexRise = extremeScenarioFactorForIndexRise;
    }

    public Double getDelta() {
        return delta;
    }

    public void setDelta(Double delta) {
        this.delta = delta;
    }

    public int getDeltaSign() {
        return deltaSign;
    }

    public void setDeltaSign(int deltaSign) {
        this.deltaSign = deltaSign;
    }

    public int getPriceScanRangePercent() {
        return priceScanRangePercent;
    }

    public void setPriceScanRangePercent(int priceScanRangePercent) {
        this.priceScanRangePercent = priceScanRangePercent;
    }

    public int getStandardDeviationFluctuation() {
        return standardDeviationFluctuation;
    }

    public void setStandardDeviationFluctuation(int standardDeviationFluctuation) {
        this.standardDeviationFluctuation = standardDeviationFluctuation;
    }

    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }
}
