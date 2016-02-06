package com.controller.strategy;

import com.DB.MongoDBStrategyDAO;
import com.google.gson.Gson;
import com.model.strategy.Strategy;
import com.mongodb.MongoClient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DeleteStrategyServlet extends HttpServlet {

    private static final long serialVersionUID = 7L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null || "".equals(id)) {
            throw new ServletException("id missing for delete operation");
        }
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        MongoDBStrategyDAO strategyDAO = new MongoDBStrategyDAO(mongo);
        Strategy strategy = new Strategy();
        strategy.setId(id);
        strategyDAO.deleteStrategy(strategyDAO.readStrategy(strategy));
        System.out.println("strategy deleted successfully with id = " + id);
        request.setAttribute("success", "strategy deleted successfully");
        List<Strategy> strategies = strategyDAO.readAllStrategies();
        request.setAttribute("strategies", strategies);

        String json = new Gson().toJson(strategies);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }

}