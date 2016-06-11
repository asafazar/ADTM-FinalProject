package com.model.actions;

public class Purchase {
    private Action purchaseStrike;
    private Action executeStrike;
    private int buyNumber;
    private int writeNumber;
    private boolean isExecuted;
    private String strategyId;
    private int buyPurchasePremium;
    private int writePurchasePremium;
    private int buyExecutePremium;
    private int writeExecutePremium;

    public Action getExecuteStrike() {
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

    public void setExecuteStrike(Action executeStrike) {
        this.executeStrike = executeStrike;
    }

    public Action getPurchaseStrike() {
        return purchaseStrike;
    }

    public void setPurchaseStrike(Action purchaseStrike) {
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