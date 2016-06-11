package com.controller.actions;

import com.DB.MongoDBActionDAO;
import com.google.gson.Gson;
import com.model.actions.Action;
import com.model.utils.Constants;
import com.mongodb.MongoClient;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;
import java.util.Date;
import java.util.List;

public class EditActionServlet extends HttpServlet {

    private static final long serialVersionUID = -6554920927964049383L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null || "".equals(id)) {
            throw new ServletException("id missing for edit action operation");
        }
        System.out.println("Action edit requested with id=" + id);
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        MongoDBActionDAO actionDAO = new MongoDBActionDAO(mongo);

        Action action;
        action = actionDAO.readAction(id);
        request.setAttribute("action", action);
        List<Action> actions = actionDAO.readAllActions();
        request.setAttribute("actions", actions);

        String json = new Gson().toJson(actions);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id"); // keep it non-editable in UI
        if (id == null || "".equals(id)) {
            throw new ServletException("id missing for edit operation");
        }

        String contractName = request.getParameter("contractName");
        Constants.ACTION_NUMBER actionNumber = Constants.ACTION_NUMBER.getValue(Integer.valueOf((String)request.getParameter("actionNumber")));
        int maofValue = Integer.valueOf((String)request.getParameter("maofValue"));
        int actionValue = Integer.valueOf((String)request.getParameter("actionValue"));
        String name = request.getParameter("name");
        int amount = Integer.valueOf((String)request.getParameter("amount"));
        int actionPrice = Integer.valueOf((String)request.getParameter("actionPrice"));
        int totalPrice = amount * actionPrice;
        boolean isWeekly = ((String)request.getParameter("optionType")).equals("1");
        Time time = Time.valueOf(request.getParameter("Time"));;
        String dateStr = request.getParameter("Date");
        Date date = new Date(Integer.valueOf(dateStr.substring(0,3)),
                Integer.valueOf(dateStr.substring(4,5)), Integer.valueOf(dateStr.substring(6,7)));
        boolean isExecuted = false;
        String strategyId = request.getParameter("strategyIs");
        int actionExecutingPrice = Integer.valueOf((String)request.getParameter("actionExecutingPrice"));;
        int totalExecutingPrice = Integer.valueOf((String)request.getParameter("TotalExecutingPrice"));

        if (contractName == null || contractName.equals(""))
        {
            throw new ServletException("id missing for edit action operation");
        } else {
            MongoClient mongo = (MongoClient) request.getServletContext()
                    .getAttribute("MONGO_CLIENT");
            MongoDBActionDAO actionDAO = new MongoDBActionDAO(mongo);
            Action action = new Action();
            action.setWeekly(isWeekly);
            request.setAttribute("success", "Stock edited Successfully");
            List<Action> actions = actionDAO.readAllActions();
            request.setAttribute("actions", actions);

            String json = new Gson().toJson(actions);
            response.setContentType("application/json");
            response.getWriter().write(json);
        }
    }

}