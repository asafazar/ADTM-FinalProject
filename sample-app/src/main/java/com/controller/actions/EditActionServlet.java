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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@WebServlet("/editAction")
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

        AbstractAction action;
        action = actionDAO.readAction(id);
        request.setAttribute("action", action);
        List<AbstractAction> actions = actionDAO.readAllActions();
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
        int actionNumber = Integer.valueOf((String)request.getParameter("actionNumber"));
        int maofValue = Integer.valueOf((String)request.getParameter("maofValue"));
        int actionValue = Integer.valueOf((String)request.getParameter("actionValue"));
        String name = request.getParameter("name");
        int amount = Integer.valueOf((String)request.getParameter("amount"));
        int actionPrice = Integer.valueOf((String)request.getParameter("actionPrice"));
        int totalPrice = amount * actionPrice;
        int optionType = Integer.valueOf((String)request.getParameter("optionType"));
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
            action.setOptionType(optionType);
            action.setTime(time);
            action.setDate(date);
            action.setIsExecuted(isExecuted);
            action.setStrategyId(strategyId);
            action.setActionExecutingPrice(actionExecutingPrice);
            action.setTotalExecutingPrice(totalExecutingPrice);
            actionDAO.updateAction(action);
            System.out.println("Action edited Successfully with id = "+action.getId());
            request.setAttribute("success", "Stock edited Successfully");
            List<AbstractAction> actions = actionDAO.readAllActions();
            request.setAttribute("actions", actions);

            String json = new Gson().toJson(actions);
            response.setContentType("application/json");
            response.getWriter().write(json);
        }
    }

}