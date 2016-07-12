package com.controller.strategy;

import com.DB.MongoDBPurchaseDAO;
import com.DB.MongoDBStrategyDAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.Strategy.Strategy;
import com.model.actions.Purchase;
import com.model.actions.Strike;
import com.model.utils.Constants;
import com.mongodb.MongoClient;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class AddStrategyServlet extends HttpServlet {

    private static final long serialVersionUID = 6L;

    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException
    {
        List<Purchase> strategiesPurchase = new ArrayList<>();
        Gson gson = new GsonBuilder().create();
        Strategy newStrategy = new Strategy();
        Map<String, String[]> parameters = request.getParameterMap();

        try
        {
           newStrategy.setUserId(parameters.get("userId")[0]);
            String dateStr = parameters.get("date")[0];
            Date date = new Date(Integer.valueOf(dateStr.substring(0, 4)),
                    Integer.valueOf(dateStr.substring(5, 7)), Integer.valueOf(dateStr.substring(8, 10)));
            newStrategy.setName(parameters.get("name")[0]);
            newStrategy.setComment(parameters.get("comment")[0]);
            newStrategy.setWeekly((parameters.get("isWeekly")[0]).equals("true"));
            newStrategy.setDate(date);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");

        if (!newStrategy.isWeekly())
        {
            for (Strike strike : Constants.lastStrikes)
            {
                if (strike.getDescription().contains(Constants.MONTHES_MAP.get(new Date().getMonth())))
                {
                    newStrategy.getPl().put(strike.getContractId(), 0.0);
                    Purchase currPurchase = new Purchase();
                    currPurchase.setPurchaseStrike(strike);
                    currPurchase.setCall(strike.isCall());
                    currPurchase.setStrikePrice(strike.getStrikePrice());
                    strategiesPurchase.add(currPurchase);
                }
            }
        }
        else
        {
            for (Strike strike : Constants.lastStrikes)
            {
                if (strike.getDescription().contains("W"))
                {
                    newStrategy.getPl().put(strike.getContractId(), (double)strike.getAsk1());
                }
            }
        }

        MongoDBStrategyDAO mongoDBStrategyDAO = new MongoDBStrategyDAO(mongo);
        Strategy strategy = mongoDBStrategyDAO.createStrategy(newStrategy);
        MongoDBPurchaseDAO mongoDBPurchaseDAO = new MongoDBPurchaseDAO(mongo);
        mongoDBPurchaseDAO.createPurchase(strategiesPurchase, strategy.getId());
        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(strategy, Strategy.class));
    }
}
