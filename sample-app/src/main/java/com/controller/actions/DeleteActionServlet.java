package com.controller.actions;

import com.DB.MongoDBActionDAO;
import com.google.gson.Gson;
import com.model.actions.Action;
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
                         HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null || "".equals(id)) {
            throw new ServletException("id missing for delete operation");
        }
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        MongoDBActionDAO actionDAO = new MongoDBActionDAO(mongo);
        Action action = actionDAO.readAction(id);
        actionDAO.deleteAction(action);
        System.out.println("Action deleted successfully with id = " + id);
        request.setAttribute("success", "Stock deleted successfully");
        List<Action> actions = actionDAO.readAllActions();
        request.setAttribute("actions", actions);

        String json = new Gson().toJson(actions);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }

}