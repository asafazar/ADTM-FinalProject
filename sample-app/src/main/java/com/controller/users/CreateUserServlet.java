package com.controller.users;

import com.DB.MongoDBStrategyDAO;
import com.DB.MongoDBUserDAO;
import com.google.gson.Gson;
import com.model.Strategy.Strategy;
import com.mongodb.MongoClient;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by asaf on 12/03/2016.
 */
public class CreateUserServlet extends HttpServlet {

    private final int createAction = 1;
    private final int loginAction = 2;
    private final int deleteAction = 3;

    public CreateUserServlet()
    {
        super();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        int actionNumber = Integer.parseInt(request.getParameter("password"));
        String userId;
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        MongoDBUserDAO userDAO = new MongoDBUserDAO(mongo);

        switch (actionNumber)
        {
            case (createAction):
                userId = userDAO.createUser(userName, password);
                break;
            case (loginAction):
                userId = userDAO.getUserId(userName, password);
                break;
            case (deleteAction):
                userId = "0";
                break;
            default:
                userId = "0";
                break;
        }

        if (userId != "0")
        {
            MongoDBStrategyDAO strategyDAO = new MongoDBStrategyDAO(mongo);
            List<Strategy> userStrategy = strategyDAO.getUserStrategies(userId);
            String strategyJson = new Gson().toJson(userStrategy);
            response.getWriter().write(strategyJson);
        }

        String userJson = new Gson().toJson(userId);
        response.setContentType("application/json");
        response.getWriter().write(userJson);
    }
}
