package com.model.actions;

import com.model.utils.Constants;

/**
 * Created by asaf on 02/02/2016.
 */
public class ActionCallWrite extends AbstractAction {

    public int getActionNumber()
    {
        return Constants.ACTION_CALL_WRITE;
    }

    @Override
    public void setActionNumber()
    {
        this.actionNumber = Constants.ACTION_CALL_WRITE;
    }
}
