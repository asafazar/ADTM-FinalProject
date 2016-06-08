package com.DB;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Arrays;

@WebListener
public class MongoDBContextListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        MongoClient mongo = (MongoClient) sce.getServletContext()
                .getAttribute("MONGO_CLIENT");
        mongo.close();
        System.out.println("MongoClient closed successfully");
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ServletContext ctx = sce.getServletContext();
            ServerAddress serverAddress = new ServerAddress(ctx.getInitParameter("DB_IP"));
            MongoCredential credential = MongoCredential.createCredential(
                    ctx.getInitParameter("MONGODB_USERNAME"), "admin",
                    ctx.getInitParameter("MONGODB_PASSWORD").toCharArray());
            MongoClient mongo = new MongoClient(serverAddress, Arrays.asList(credential));
            System.out.println("MongoClient initialized successfully");
            sce.getServletContext().setAttribute("MONGO_CLIENT", mongo);
        } catch (Exception e) {
            throw new RuntimeException("MongoClient init failed");
        }
    }

}