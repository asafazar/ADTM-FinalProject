package com.controller.strategy;

import com.DB.MongoDBStrategyDAO;
import com.google.gson.Gson;
import com.model.Strategy.Strategy;
import com.mongodb.MongoClient;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class EditStrategyServlet extends HttpServlet {

    private static final long serialVersionUID = -6554920927964049383L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null || "".equals(id)) {
            throw new ServletException("id missing for edit action operation");
        }
        System.out.println("strategy edit requested with id = " + id);
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        MongoDBStrategyDAO strategyDAO = new MongoDBStrategyDAO(mongo);
        Strategy strategy = new Strategy();
        strategy.setId(id);
        strategy = strategyDAO.readStrategy(strategy);
        request.setAttribute("strategy", strategy);
        List<Strategy> strategies = strategyDAO.readAllStrategies();
        request.setAttribute("strategies", strategies);

        String json = new Gson().toJson(strategies);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id"); // keep it non-editable in UI
        if (id == null || "".equals(id)) {
            throw new ServletException("id missing for edit operation");
        }

        boolean isWeekly = ((String)request.getParameter("isWeekly")).equals("1");
        String description = request.getParameter("description");
        String comment = request.getParameter("comment");
        String dateStr = request.getParameter("Date");
        Date date = new Date(Integer.valueOf(dateStr.substring(0, 3)),
                Integer.valueOf(dateStr.substring(4, 5)), Integer.valueOf(dateStr.substring(6, 7)));
        Strategy strategy = new Strategy();
        strategy.setWeekly(isWeekly);
        strategy.setName(description);
        strategy.setComment(comment);
        strategy.setDate(date);
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        MongoDBStrategyDAO strategyDAO = new MongoDBStrategyDAO(mongo);
        strategyDAO.updateStrategy(strategy);
        System.out.println("strategy edited Successfully with id = "+strategy.getId());
        request.setAttribute("success", "strategy edited Successfully");
        List<Strategy> strategies = strategyDAO.readAllStrategies();
        request.setAttribute("strategies", strategies);

        String json = new Gson().toJson(strategies);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }

}