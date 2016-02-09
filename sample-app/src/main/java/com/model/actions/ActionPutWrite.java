package com.model.actions;

import com.model.utils.Constants;

/**
 * Created by asaf on 02/02/2016.
 */
public class ActionPutWrite extends AbstractAction {

    public Constants.ACTION_NUMBER getActionNumber()
    {
        return Constants.ACTION_NUMBER.ACTION_PUT_WRITE;
    }

    @Override
    public void setActionNumber()
    {
        this.actionNumber = Constants.ACTION_NUMBER.ACTION_PUT_WRITE;
    }
}
