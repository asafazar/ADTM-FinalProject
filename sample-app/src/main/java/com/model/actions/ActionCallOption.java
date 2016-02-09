package com.model.actions;

import com.model.utils.Constants;

/**
 * Created by asaf on 02/02/2016.
 */
public class ActionCallOption extends AbstractAction {

    public Constants.ACTION_NUMBER getActionNumber()
    {
        return Constants.ACTION_NUMBER.ACTION_CALL_OPTION;
    }

    @Override
    public void setActionNumber()
    {
        this.actionNumber = Constants.ACTION_NUMBER.ACTION_CALL_OPTION;
    }
}
