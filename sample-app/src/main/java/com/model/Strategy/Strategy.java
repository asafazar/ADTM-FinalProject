package com.model.strategy;

import com.model.actions.AbstractAction;

import java.util.Date;
import java.util.List;

/**
 * Created by asaf on 04/02/2016.
 */
public class Strategy {

    private String id;
    public List<AbstractAction> actions;
    private boolean isWeekly;
    private String description;
    private String comment;
    private Date date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<AbstractAction> getActions() {
        return actions;
    }

    public void setActions(List<AbstractAction> actions) {
        this.actions = actions;
    }

    public boolean isWeekly() {
        return isWeekly;
    }

    public void setWeekly(boolean weekly) {
        isWeekly = weekly;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String descr) {
        this.description = descr;
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
    }
}
