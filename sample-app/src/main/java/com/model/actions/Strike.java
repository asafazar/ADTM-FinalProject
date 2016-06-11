package com.model.actions;

import com.model.utils.TimeCalculator;

import java.util.Date;

/**
 * Created by asaf on 02/02/2016.
 */
public class Strike {
    private int strikePrice;
    private String trade;
    private int basePrice;
    private int currentPrice;
    private Double dailyChange;
    private int ask1;
    private int askAmount1;
    private int bid1;
    private int bidAmount1;
    private Double unknown;
    private Double theoreticalValue;
    private int dailyValue;
    private int dailyCycle;
    private String dealTime;
    private int openPositions;
    private int stStockMarket;
    private Double stSkew;
    private Double stGlume;
    private int dealsNumber;
    private Double predicted;
    private Double contractsForSale;
    private Double contractsForBuy;
    private int contractsNumber;
    private long contractsId;
    private Date strikeDate;
    private Date expirationDate;
    private String description;
    private boolean isWeekly;
    private boolean isCall;

    public Strike()
    {

    }


    public boolean isCall() {
        return isCall;
    }

    public void setCall(boolean call) {
        isCall = call;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Date getStrikeDate() {
        return strikeDate;
    }

    public void setStrikeDate(Date strikeDate) {
        this.strikeDate = strikeDate;
    }

    public long getContractsId() {
        return contractsId;
    }

    public void setContractsId(long contractsId) {
        this.contractsId = contractsId;
    }

    public boolean isWeekly()
    {
        return this.isWeekly;
    }

    public void setWeekly(boolean isWeekly)
    {
        this.isWeekly = isWeekly;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date date) {
        if (isWeekly)
        {
            this.expirationDate = new TimeCalculator().getWeeklyExpirationDate(date);
        }
        else
        {
            this.expirationDate = new TimeCalculator().getMonthlyExpirationDate(date);
        }
    }

    public int getStrikePrice() {
        return strikePrice;
    }

    public void setStrikePrice(int strikePrice) {
        this.strikePrice = strikePrice;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Double getDailyChange() {
        return dailyChange;
    }

    public void setDailyChange(Double dailyChange) {
        this.dailyChange = dailyChange;
    }

    public int getAsk1() {
        return ask1;
    }

    public void setAsk1(int ask1) {
        this.ask1 = ask1;
    }

    public int getAskAmount1() {
        return askAmount1;
    }

    public void setAskAmount1(int askAmount1) {
        this.askAmount1 = askAmount1;
    }

    public int getBid1() {
        return bid1;
    }

    public void setBid1(int bid1) {
        this.bid1 = bid1;
    }

    public int getBidAmount1() {
        return bidAmount1;
    }

    public void setBidAmount1(int bidAmount1) {
        this.bidAmount1 = bidAmount1;
    }

    public Double getUnknown() {
        return unknown;
    }

    public void setUnknown(Double unknown) {
        this.unknown = unknown;
    }

    public Double getTheoreticalValue() {
        return theoreticalValue;
    }

    public void setTheoreticalValue(Double theoreticalValue) {
        this.theoreticalValue = theoreticalValue;
    }

    public int getDailyValue() {
        return dailyValue;
    }

    public void setDailyValue(int dailyValue) {
        this.dailyValue = dailyValue;
    }

    public int getDailyCycle() {
        return dailyCycle;
    }

    public void setDailyCycle(int dailyCycle) {
        this.dailyCycle = dailyCycle;
    }

    public String getDealTime() {
        return dealTime;
    }

    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
    }

    public int getOpenPositions() {
        return openPositions;
    }

    public void setOpenPositions(int openPositions) {
        this.openPositions = openPositions;
    }

    public int getStStockMarket() {
        return stStockMarket;
    }

    public void setStStockMarket(int stStockMarket) {
        this.stStockMarket = stStockMarket;
    }

    public Double getStSkew() {
        return stSkew;
    }

    public void setStSkew(Double stSkew) {
        this.stSkew = stSkew;
    }

    public Double getStGlume() {
        return stGlume;
    }

    public void setStGlume(Double stGlume) {
        this.stGlume = stGlume;
    }

    public int getDealsNumber() {
        return dealsNumber;
    }

    public void setDealsNumber(int dealsNumber) {
        this.dealsNumber = dealsNumber;
    }

    public Double getPredicted() {
        return predicted;
    }

    public void setPredicted(Double predicted) {
        this.predicted = predicted;
    }

    public Double getContractsForSale() {
        return contractsForSale;
    }

    public void setContractsForSale(Double contractsForSale) {
        this.contractsForSale = contractsForSale;
    }

    public Double getContractsForBuy() {
        return contractsForBuy;
    }

    public void setContractsForBuy(Double contractsForBuy) {
        this.contractsForBuy = contractsForBuy;
    }

    public int getContractsNumber() {
        return contractsNumber;
    }

    public void setContractsNumber(int contractsNumber) {
        this.contractsNumber = contractsNumber;
    }

}
