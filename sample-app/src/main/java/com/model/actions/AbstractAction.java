package com.model.actions;

import com.model.utils.Constants;
import com.model.utils.TimeCalculator;

import java.sql.Time;
import java.util.Date;

/**
 * Created by asaf on 02/02/2016.
 */
public abstract class AbstractAction {
    private String id;
    private String contractName;
    public Constants.ACTION_NUMBER actionNumber;
    private int maofValue;
    private int actionValue;
    private String name;
    private int amount;
    private int actionPrice;
    private int totalPrice;
    private boolean isWeekly;
    private Time time;
    private Date date;
    private boolean isExecuted;
    private String strategyId;
    private int actionExecutingPrice;
    private int totalExecutingPrice;
    private Date expirationDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Constants.ACTION_NUMBER getActionNumber() {
        return actionNumber;
    }

    public int getMaofValue() {
        return maofValue;
    }

    public void setMaofValue(int maofValue) {
        this.maofValue = maofValue;
    }

    public int getActionValue() {
        return actionValue;
    }

    public void setActionValue(int actionValue) {
        this.actionValue = actionValue;
    }


    public int getActionPrice() {
        return actionPrice;
    }

    public void setActionPrice(int actionPrice) {
        this.actionPrice = actionPrice;
    }

    public boolean isWeekly()
    {
        return this.isWeekly;
    }

    public void setWeekly(boolean isWeekly)
    {
        this.isWeekly = isWeekly;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
        setExpirationDate(date);
    }

    public boolean getIsExecuted(){
        return isExecuted;
    }

    public void setIsExecuted(boolean isExecuted)
    {
        this.isExecuted = isExecuted;
    }

    public String getStrategyId()
    {
        return this.strategyId;
    }

    public void setStrategyId(String strategyId)
    {
        this.strategyId = strategyId;
    }

    public int getActionExecutingPrice()
    {
        return this.actionExecutingPrice;
    }

    public void setActionExecutingPrice(int executingPrice)
    {
        this.actionExecutingPrice = executingPrice;
    }

    public int getTotalExecutingPrice()
    {
        return this.totalExecutingPrice;
    }

    public void setTotalExecutingPrice(int totalExecutingPrice)
    {
        this.totalExecutingPrice = totalExecutingPrice;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    protected void setExpirationDate(Date date) {
        if (isWeekly)
        {
            this.expirationDate = new TimeCalculator().getWeeklyExpirationDate(date);
        }
        else
        {
            this.expirationDate = new TimeCalculator().getMonthlyExpirationDate(date);
        }
    }

    public abstract void setActionNumber();
}
