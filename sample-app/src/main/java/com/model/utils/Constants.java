package com.model.utils;

import com.model.actions.Strike;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by asaf on 02/02/2016.
 */
public class Constants {

    public static final String[] ACTION_FIELDS = {
            "strikePrice", "trade","basePrice","currentPrice","dailyChange", "askAmount1", "ask1", "bidAmount1", "bid1",
            "unknown","theoreticalValue","dailyValue","dailyCycle","dealTime","openPositions","stStockMarket",
            "stSkew","stGlume","dealsNumber","predicted","contractsForSale","contractsForBuy",
            "contractsNumber", "contractId"};

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
    public static int getBidByContractId(Long contractId)
    {
        for (Strike strike : lastStrikes)
        {
          if (strike.getContractId() == contractId)
          {
              return strike.getBid1();
          }
        }

        return 0;
    }

    public static int getAskByContractId(Long contractId)
    {
        for (Strike strike : lastStrikes)
        {
            if (strike.getContractId() == contractId)
            {
                return strike.getAsk1();
            }
        }

        return 0;
    }
}
