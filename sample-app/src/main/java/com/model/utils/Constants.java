package com.model.utils;

/**
 * Created by asaf on 02/02/2016.
 */
public class Constants {
    public enum  ACTION_NUMBER
    {
        ACTION_CALL_OPTION(1),
        ACTION_CALL_WRITE(2),
        ACTION_PUT_OPTION(3),
        ACTION_PUT_WRITE(4);
        private int actionValue;
        private ACTION_NUMBER(int action){
            this.actionValue = action;
        }
        public int getActionValue()
        {
            return actionValue;
        }
        public boolean compare(int i){return actionValue == i;}
        public static ACTION_NUMBER getValue(int actionValue)
        {
            ACTION_NUMBER[] actions = ACTION_NUMBER.values();
            for(int i = 0; i < actions.length; i++)
            {
                if(actions[i].compare(actionValue))
                    return actions[i];
            }
            return ACTION_CALL_OPTION;
        }
    };

    public static final int OPTION_TYPE_DAILY = 1;
    public static final int OPTION_TYPE_WEEKLY = 2;
    public static final int OPTION_TYPE_MONTHLY = 3;
}
