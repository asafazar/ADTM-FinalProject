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

public class DeleteActionServlet extends HttpServlet {

    private static final long serialVersionUID = 3L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException
    {
        String id = request.getParameter("id");
        if (id == null || "".equals(id))
        {
            throw new ServletException("id missing for delete operation");
        }

        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        MongoDBActionDAO actionDAO = new MongoDBActionDAO(mongo);
        Strike strike = actionDAO.readAction(id);
        actionDAO.deleteAction(strike);
        System.out.println("Strike deleted successfully with id = " + id);
        request.setAttribute("success", "Stock deleted successfully");
        List<Strike> strikes = actionDAO.readAllActions();
        request.setAttribute("strikes", strikes);
        String json = new Gson().toJson(strikes);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}