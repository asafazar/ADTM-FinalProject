package com.model.actions;

import java.sql.Time;
import java.util.Date;

/**
 * Created by asaf on 02/02/2016.
 */
public abstract class AbstractAction {
    private String id;
    private String contractName;
    public int action;
    private int maofValue;
    private int actionValue;
    private String name;
    private int amount;
    private int actionPrice;
    private int totalPrice;
    private int optionType;
    private Time time;
    private Date date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String setContractName() {
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

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
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

    public abstract void setAction();
}
