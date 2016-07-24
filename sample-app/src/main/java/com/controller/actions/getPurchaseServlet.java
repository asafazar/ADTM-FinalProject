package com.controller.actions;

import com.DB.MongoDBPurchaseDAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.actions.Purchase;
import com.model.utils.Constants;
import com.mongodb.MongoClient;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by asaf on 13/06/2016.
 */
public class GetPurchaseServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String strategyId = request.getParameter("id");
        Gson gson = new GsonBuilder().create();
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        MongoDBPurchaseDAO mongoDBPurchaseDAO = new MongoDBPurchaseDAO(mongo);
        List<Purchase> purchaseList = mongoDBPurchaseDAO.getPurchaseForStrategy(strategyId);

        String purchasesString = "[";

        for (Purchase currPurchase : purchaseList)
        {
            currPurchase.setBid1(Constants.getBidByContractId(currPurchase.getPurchaseStrike().getContractId()));
            currPurchase.setAsk1(Constants.getAskByContractId(currPurchase.getPurchaseStrike().getContractId()));
            purchasesString += gson.toJson(currPurchase);
            purchasesString += ",";
        }

        purchasesString = purchasesString.substring(0, purchasesString.length() - 1);
        purchasesString += "]";
        response.setContentType("application/json");
        response.getWriter().write(purchasesString);
    }
}
