package com.controller.actions;

import com.DB.MongoDBActionDAO;
import com.google.gson.Gson;
import com.model.actions.Strike;
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

public class AddActionServlet extends HttpServlet {

    private static final long serialVersionUID = 2L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

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
        int actionExecutingPrice = 0;
        int totalExecutingPrice = 0;

        if (contractName == null || "".equals(contractName)) {
            throw new ServletException("contract name missing for add operation");
        }
        else
        {
            Strike strike = new Strike();

            strike.setWeekly(isWeekly);

            MongoClient mongo = (MongoClient) request.getServletContext()
                    .getAttribute("MONGO_CLIENT");
            MongoDBActionDAO actionDAO = new MongoDBActionDAO(mongo);
            actionDAO.createAction(strike);
            System.out.println("Strike added requested with contract name = " + contractName);
            request.setAttribute("strike", strike);
            List<Strike> strikes = actionDAO.readAllActions();
            request.setAttribute("strikes", strikes);
            String json = new Gson().toJson(strikes);
            response.setContentType("application/json");
            response.getWriter().write(json);

        }
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {


    }

}