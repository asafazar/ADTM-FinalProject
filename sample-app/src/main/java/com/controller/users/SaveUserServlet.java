package com.controller.users;

import com.DB.MongoDBUserDAO;
import com.mongodb.MongoClient;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by asaf on 12/04/2016.
 */
public class SaveUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        MongoDBUserDAO mongoDBUserDAO = new MongoDBUserDAO(mongo);
        mongoDBUserDAO.saveUser(
        request.getParameter("name"),
        request.getParameter("mail"),
        request.getParameter("id"),
        request.getParameter("pictureLink")
        );

    }
}
