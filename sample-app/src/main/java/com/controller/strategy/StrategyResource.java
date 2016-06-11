package com.controller.strategy;

import com.DB.MongoDBStrategyDAO;
import com.controller.users.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.Strategy.Strategy;
import com.model.actions.Strike;
import com.model.utils.Constants;
import com.mongodb.MongoClient;
import com.nimbusds.jose.JOSEException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

@Path("/strategy")
public class StrategyResource {

    public StrategyResource() {
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(String strategy, @Context final HttpServletRequest request)
            throws JOSEException {
        Gson gson = new GsonBuilder().create();
        Strategy newStrategy = gson.fromJson(strategy, Strategy.class);
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        for (Strike strike : Constants.lastStrikes)
        {
            if (strike.getDescription().contains("W") ||
                    strike.getDescription().contains(Constants.MONTHES_MAP.get(new Date().getMonth())))
            {
                newStrategy.getPl().put(strike, 0.0);
            }
        }

        MongoDBStrategyDAO mongoDBStrategyDAO = new MongoDBStrategyDAO(mongo);
        Strategy completeStrategy = mongoDBStrategyDAO.createStrategy(newStrategy);
        return Response.status(Response.Status.CREATED).entity(completeStrategy).build();
    }

    @POST
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(String strategy, @Context final HttpServletRequest request)
            throws JOSEException {
        Gson gson = new GsonBuilder().create();
        Strategy deleteStrategy = gson.fromJson(strategy, Strategy.class);
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        MongoDBStrategyDAO mongoDBStrategyDAO = new MongoDBStrategyDAO(mongo);
        mongoDBStrategyDAO.deleteStrategy(deleteStrategy.getId());
        return Response.status(Response.Status.OK).build();
    }


    @POST
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(String strategy, @Context final HttpServletRequest request)
            throws JOSEException {
        Gson gson = new GsonBuilder().create();
        Strategy currStrategy = gson.fromJson(strategy, Strategy.class);
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        MongoDBStrategyDAO mongoDBStrategyDAO = new MongoDBStrategyDAO(mongo);
        mongoDBStrategyDAO.updateStrategy(currStrategy);
        return Response.status(Response.Status.OK).build();
    }


    @POST
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response all(String user, @Context final HttpServletRequest request)
            throws JOSEException {
        Gson gson = new GsonBuilder().create();
        User currUser = gson.fromJson(user, User.class);
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        MongoDBStrategyDAO mongoDBStrategyDAO = new MongoDBStrategyDAO(mongo);
        List<Strategy> strategies = mongoDBStrategyDAO.getUserStrategies(currUser.getId());
        return Response.status(Response.Status.OK).entity(strategies).build();
    }
}

