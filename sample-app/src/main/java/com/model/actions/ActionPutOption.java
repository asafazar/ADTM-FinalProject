package com.model.actions;

import com.model.utils.Constants;

/**
 * Created by asaf on 02/02/2016.
 */
public class ActionPutOption extends AbstractAction {

    public int getActionNumber()
    {
        return Constants.ACTION_PUT_OPTION;
    }

    @Override
    public void setActionNumber()
    {
        this.actionNumber = Constants.ACTION_PUT_OPTION;
    }
}
