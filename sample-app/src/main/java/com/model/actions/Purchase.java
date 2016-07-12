package com.model.actions;

public class Purchase {
    private String id;
    private Strike purchaseStrike;
    private Strike executeStrike;
    private int strikePrice;
    private int ask1;
    private int bid1;
    private int buyNumber;
    private int writeNumber;
    private boolean isExecuted;
    private String strategyId;
    private int buyPurchasePremium;
    private int writePurchasePremium;
    private int buyExecutePremium;
    private int writeExecutePremium;
    private boolean isCall;

    public int getStrikePrice() {
        return strikePrice;
    }

    public void setStrikePrice(int strikePrice) {
        this.strikePrice = strikePrice;
    }

    public int getAsk1() {
        return ask1;
    }

    public void setAsk1(int ask1) {
        this.ask1 = ask1;
    }

    public int getBid1() {
        return bid1;
    }

    public void setBid1(int bid1) {
        this.bid1 = bid1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isCall() {
        return isCall;
    }

    public void setCall(boolean call) {
        isCall = call;
    }

    public Strike getExecuteStrike() {
        return executeStrike;
    }

    public int getBuyExecutePremium() {
        return buyExecutePremium;
    }

    public void setBuyExecutePremium(int buyExecutePremium) {
        this.buyExecutePremium = buyExecutePremium;
    }

    public int getWriteExecutePremium() {
        return writeExecutePremium;
    }

    public void setWriteExecutePremium(int writeExecutePremium) {
        this.writeExecutePremium = writeExecutePremium;
    }

    public void setExecuteStrike(Strike executeStrike) {
        this.executeStrike = executeStrike;
    }

    public Strike getPurchaseStrike() {
        return purchaseStrike;
    }

    public void setPurchaseStrike(Strike purchaseStrike) {
        this.purchaseStrike = purchaseStrike;
    }

    public int getBuyNumber() {
        return buyNumber;
    }

    public void setBuyNumber(int buyNumber) {
        this.buyNumber = buyNumber;
    }

    public int getWriteNumber() {
        return writeNumber;
    }

    public void setWriteNumber(int writeNumber) {
        this.writeNumber = writeNumber;
    }

    public int getBuyPurchasePremium() {
        return buyPurchasePremium;
    }

    public void setBuyPurchasePremium(int buyPurchasePremium) {
        this.buyPurchasePremium = buyPurchasePremium;
    }

    public int getWritePurchasePremium() {
        return writePurchasePremium;
    }

    public void setWritePurchasePremium(int writePurchasePremium) {
        this.writePurchasePremium = writePurchasePremium;
    }

    public String getStrategyId()
    {
        return this.strategyId;
    }

    public void setStrategyId(String strategyId)
    {
        this.strategyId = strategyId;
    }


    public boolean isExecuted() {
        return isExecuted;
    }

    public void setExecuted(boolean executed) {
        isExecuted = executed;
    }

}