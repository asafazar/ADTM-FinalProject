package com.model.actions;

import java.sql.Time;
import java.util.Date;

/**
 * Created by asaf on 02/02/2016.
 */
public abstract class AbstractAction {
    private String id;
    private String contractName;
    public int actionNumber;
    private int maofValue;
    private int actionValue;
    private String name;
    private int amount;
    private int actionPrice;
    private int totalPrice;
    private int optionType;
    private Time time;
    private Date date;
    private boolean isExecuted;
    private String strategyId;
    private int actionExecutingPrice;
    private int totalExecutingPrice;

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

    public int getActionNumber() {
        return actionNumber;
    }

    public void setActionNumber(int actionNumber) {
        this.actionNumber = actionNumber;
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

    public int getOptionType() {
        return optionType;
    }

    public void setOptionType(int optionType) {
        this.optionType = optionType;
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

    public abstract void setActionNumber();
}
