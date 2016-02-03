package com.model.actions;

import com.model.utils.Constants;

/**
 * Created by asaf on 02/02/2016.
 */
public class ActionCallWrite extends AbstractAction {

    public int getAction()
    {
        return Constants.ACTION_CALL_WRITE;
    }

    @Override
    public void setAction()
    {
        this.action = Constants.ACTION_CALL_WRITE;
    }
}
