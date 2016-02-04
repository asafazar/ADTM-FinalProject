package com.model.Strategy;

import com.model.actions.AbstractAction;

import java.util.List;

/**
 * Created by asaf on 04/02/2016.
 */
public class Strategy {

    private String id;
    public List<AbstractAction> actions;
    private boolean isWeekly;

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

}
