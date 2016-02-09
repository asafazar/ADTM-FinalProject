package com.controller.actions;

import com.DB.MongoDBActionDAO;
import com.google.gson.Gson;
import com.model.actions.AbstractAction;
import com.model.actions.ActionCallOption;
import com.model.actions.ActionCallWrite;
import com.model.actions.ActionPutOption;
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
        int actionNumber = Integer.valueOf((String)request.getParameter("actionNumber"));
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
            AbstractAction action;
            switch (actionNumber)
            {
                case (Constants.ACTION_CALL_OPTION):
                    action = new ActionCallOption();
                    break;
                case (Constants.ACTION_CALL_WRITE):
                    action = new ActionCallWrite();
                    break;
                case (Constants.ACTION_PUT_OPTION):
                    action = new ActionPutOption();
                    break;
                case (Constants.ACTION_PUT_WRITE):
                    action = new ActionCallWrite();
                    break;
                default:
                    action = new ActionCallOption();
            }

            action.setContractName(contractName);
            action.setActionNumber(actionNumber);
            action.setMaofValue(maofValue);
            action.setActionValue(actionValue);
            action.setName(name);
            action.setAmount(amount);
            action.setActionPrice(actionPrice);
            action.setTotalPrice(totalPrice);
            action.setWeekly(isWeekly);
            action.setTime(time);
            action.setDate(date);
            action.setIsExecuted(isExecuted);
            action.setStrategyId(strategyId);
            action.setActionExecutingPrice(actionExecutingPrice);
            action.setTotalExecutingPrice(totalExecutingPrice);

            MongoClient mongo = (MongoClient) request.getServletContext()
                    .getAttribute("MONGO_CLIENT");
            MongoDBActionDAO actionDAO = new MongoDBActionDAO(mongo);
            actionDAO.createAction(action);
            System.out.println("Action added requested with contract name = " + contractName);
            request.setAttribute("action", action);
            List<AbstractAction> actions = actionDAO.readAllActions();
            request.setAttribute("actions", actions);
            String json = new Gson().toJson(actions);
            response.setContentType("application/json");
            response.getWriter().write(json);

        }
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {


    }

}