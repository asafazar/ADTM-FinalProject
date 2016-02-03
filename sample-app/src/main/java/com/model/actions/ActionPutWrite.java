package com.model.actions;

import com.model.utils.Constants;

/**
 * Created by asaf on 02/02/2016.
 */
public class ActionPutWrite extends AbstractAction {

    public int getAction()
    {
        return Constants.ACTION_PUT_WRITE;
    }

    @Override
    public void setAction()
    {
        this.action = Constants.ACTION_PUT_WRITE;
    }
}
