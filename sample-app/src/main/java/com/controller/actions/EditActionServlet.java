package com.controller.actions;

import com.DB.MongoDBActionDAO;
import com.google.gson.Gson;
import com.model.actions.Strike;
import com.mongodb.MongoClient;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EditActionServlet extends HttpServlet {

    private static final long serialVersionUID = -6554920927964049383L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException
    {
        String id = request.getParameter("id");

        if (id == null || "".equals(id))
        {
            throw new ServletException("id missing for edit strike operation");
        }

        System.out.println("Strike edit requested with id=" + id);
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        MongoDBActionDAO actionDAO = new MongoDBActionDAO(mongo);
        Strike strike;
        strike = actionDAO.readAction(id);
        request.setAttribute("strike", strike);
        List<Strike> strikes = actionDAO.readAllActions();
        request.setAttribute("strikes", strikes);
        String json = new Gson().toJson(strikes);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException
    {
        String id = request.getParameter("id");

        if (id == null || "".equals(id))
        {
            throw new ServletException("id missing for edit operation");
        }

        String contractName = request.getParameter("contractName");
        boolean isWeekly = (request.getParameter("optionType")).equals("1");

        if (contractName == null || contractName.equals(""))
        {
            throw new ServletException("id missing for edit action operation");
        }
        else
        {
            MongoClient mongo = (MongoClient) request.getServletContext()
                    .getAttribute("MONGO_CLIENT");
            MongoDBActionDAO actionDAO = new MongoDBActionDAO(mongo);
            Strike strike = new Strike();
            strike.setWeekly(isWeekly);
            request.setAttribute("success", "Stock edited Successfully");
            List<Strike> strikes = actionDAO.readAllActions();
            request.setAttribute("strikes", strikes);
            String json = new Gson().toJson(strikes);
            response.setContentType("application/json");
            response.getWriter().write(json);
        }
    }
}