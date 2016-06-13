package com.model.utils;

import com.model.actions.Strike;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static final String[] ACTION_FIELDS = {
            "strikePrice", "trade","basePrice","currentPrice","dailyChange", "askAmount1", "ask1", "bidAmount1", "bid1",
            "unknown","theoreticalValue","dailyValue","dailyCycle","dealTime","openPositions","stStockMarket",
            "stSkew","stGlume","dealsNumber","predicted","contractsForSale","contractsForBuy",
            "contractsNumber", "contractId"};

    public static final int OPTION_TYPE_DAILY = 1;
    public static final int OPTION_TYPE_WEEKLY = 2;
    public static final int OPTION_TYPE_MONTHLY = 3;
    public static final Map<Integer, String> MONTHES_MAP;
    static {
        Map<Integer, String> temp = new HashMap<>();
        temp.put(0, "JAN");
        temp.put(1, "FEB");
        temp.put(2, "MAR");
        temp.put(3, "APR");
        temp.put(4, "MAY");
        temp.put(5, "JUN");
        temp.put(6, "JUL");
        temp.put(7, "AUG");
        temp.put(8, "SEP");
        temp.put(9, "OCT");
        temp.put(10, "NOV");
        temp.put(11, "DEC");
        MONTHES_MAP = temp;
    }

    public static List<Strike> lastStrikes;
}
