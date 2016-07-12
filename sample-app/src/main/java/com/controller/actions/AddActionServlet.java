package com.controller.actions;

import com.DB.MongoDBStrikesDAO;
import com.google.gson.Gson;
import com.model.actions.Strike;
import com.mongodb.MongoClient;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AddActionServlet extends HttpServlet {

    private static final long serialVersionUID = 2L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        String contractName = request.getParameter("contractName");
        boolean isWeekly = (request.getParameter("optionType")).equals("1");

        if (contractName == null || "".equals(contractName))
        {
            throw new ServletException("contract name missing for add operation");
        }
        else
        {
            Strike strike = new Strike();
            strike.setWeekly(isWeekly);
            MongoClient mongo = (MongoClient) request.getServletContext()
                    .getAttribute("MONGO_CLIENT");
            MongoDBStrikesDAO actionDAO = new MongoDBStrikesDAO(mongo);
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
}