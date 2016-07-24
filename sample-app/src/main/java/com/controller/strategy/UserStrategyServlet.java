package com.controller.strategy;

import com.DB.MongoDBStrategyDAO;
import com.model.user.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.Strategy.Strategy;
import com.mongodb.MongoClient;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by asaf on 12/06/2016.
 */
public class UserStrategyServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException
    {
        Gson gson = new GsonBuilder().create();
        User loginUser = new User();
        loginUser.setEmail(request.getParameter("userid").toString());
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        MongoDBStrategyDAO mongoDBStrategyDAO = new MongoDBStrategyDAO(mongo);
        List<Strategy> strategies = mongoDBStrategyDAO.getUserStrategies(loginUser.getEmail());
        String strategiesAsString = "[";

        for (Strategy strategy : strategies)
        {
            strategiesAsString += gson.toJson(strategy, Strategy.class).toString();
            strategiesAsString += ",";
        }

        strategiesAsString = strategiesAsString.substring(0, strategiesAsString.length() - 1);
        strategiesAsString += "]";
        response.setContentType("application/json");
        response.getWriter().write(strategiesAsString);
    }
}
