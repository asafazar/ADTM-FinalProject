package com.controller.actions;

import com.DB.MongoDBPurchaseDAO;
import com.google.gson.Gson;
import com.model.actions.Purchase;
import com.mongodb.MongoClient;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by asaf on 14/06/2016.
 */
public class UpdatePurchaseServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException
    {
        Map<String, String[]> parameters = request.getParameterMap();
        Gson gson = new Gson();
        Purchase purchase = gson.fromJson(parameters.get("id")[0], Purchase.class);
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        MongoDBPurchaseDAO mongoDBPurchaseDAO = new MongoDBPurchaseDAO(mongo);
        Purchase updatedPurchase = mongoDBPurchaseDAO.updatePurchase(purchase);
        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(updatedPurchase, Purchase.class));
    }
}
