package com.model.Strategy;

import com.model.actions.Purchase;
import com.model.utils.TimeCalculator;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by asaf on 04/02/2016.
 */
public class Strategy {

    private String id;
    public List<Purchase> strikes;
    private boolean isWeekly;
    private String name;
    private String comment;
    private Date date;
    private Date expirationDate;
    private String userId;
    private Map<Long, Double> pl = new HashMap<>();

    public Map<Long, Double> getPl() {
        return pl;
    }

    public void setPl(Map<Long, Double> pl) {
        this.pl = pl;
    }

    public List<Purchase> getStrikes() {
        return strikes;
    }

    public void setStrikes(List<Purchase> strikes) {
        this.strikes = strikes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Purchase> getActions() {
        return strikes;
    }

    public void setActions(List<Purchase> actions) {
        this.strikes = actions;
    }

    public boolean isWeekly() {
        return isWeekly;
    }

    public void setWeekly(boolean weekly) {
        isWeekly = weekly;
    }

    public String getName() {
        return name;
    }

    public void setName(String descr) {
        this.name = descr;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate()
    {
        return this.date;
    }

    public void setDate(Date creationStrategyDate)
    {
        this.date = creationStrategyDate;
        setExpirationDate(creationStrategyDate);
    }

    public Date getExpirationDate()
    {
        return this.expirationDate;
    }

    protected void setExpirationDate(Date creationStrategyDate)
    {
        if (isWeekly)
        {
            this.expirationDate = new TimeCalculator().getWeeklyExpirationDate(creationStrategyDate);
        }
        else
        {
            this.expirationDate = new TimeCalculator().getMonthlyExpirationDate(creationStrategyDate);
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
