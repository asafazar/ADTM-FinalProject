package com.model.stocks;

import java.sql.Time;
import java.util.Date;

/**
 * Created by asaf on 26/01/2016.
 */
public class Stock
{
    private String ID;
    private int contractNumber;
    private int amount;
    private String assetCode;
    private String buyCodeNumber;
    private String sellCodeNumber;
    private String assetName;
    private Time time;
    private String tradeNumber;
    private boolean tradeStatus;
    private boolean participateCode;
    private boolean matchingTradeDeal;
    private double price;
    private Date date;

    public String getID() {
        return ID;
    }

    public void setID(String id) {
        this.ID = id;
    }

    public int getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(int contractNumber) {
        this.contractNumber = contractNumber;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getBuyCodeNumber() {
        return buyCodeNumber;
    }

    public void setBuyCodeNumber(String buyCodeNumber) {
        this.buyCodeNumber = buyCodeNumber;
    }

    public String getSellCodeNumber() {
        return sellCodeNumber;
    }

    public void setSellCodeNumber(String sellCodeNumber) {
        this.sellCodeNumber = sellCodeNumber;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getTradeNumber() {
        return tradeNumber;
    }

    public void setTradeNumber(String tradeNumber) {
        this.tradeNumber = tradeNumber;
    }

    public boolean isTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(boolean tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public boolean isParticipateCode() {
        return participateCode;
    }

    public void setParticipateCode(boolean participateCode) {
        this.participateCode = participateCode;
    }

    public boolean isMatchingTradeDeal() {
        return matchingTradeDeal;
    }

    public void setMatchingTradeDeal(boolean matchingTradeDeal) {
        this.matchingTradeDeal = matchingTradeDeal;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
