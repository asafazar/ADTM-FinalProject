package com.controller.strategy;

import com.DB.MongoDBStrategyDAO;
import com.DB.MongoDBUserDAO;
import com.controller.auth.AuthUtils;
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
import java.text.ParseException;
import java.util.List;

@Path("/strategy")
public class StrategyResource {

    public StrategyResource() {
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(String strategy,String userId, @Context final HttpServletRequest request)
            throws JOSEException {
        Gson gson = new GsonBuilder().create();
        Strategy newStrategy = gson.fromJson(strategy, Strategy.class);
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        if (!newStrategy.isWeekly())
        {
            for (Strike strike : Constants.lastStrikes)
            {
                // just for testing
                if (strike.getDescription().contains("APR"))
                //if (strike.getDescription().contains(Constants.MONTHES_MAP.get(new Date().getMonth())))
                {
                    newStrategy.getPl().put(strike.getContractId(), 0.0);
                }
            }
        }
        else {
            for (Strike strike : Constants.lastStrikes)
            {
                if (strike.getDescription().contains("W"))
                {
                    newStrategy.getPl().put(strike.getContractId(), 0.0);
                }
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
    public Response all(@Context HttpServletRequest request)  throws ParseException, JOSEException {
        User currUser = getAuthUser(request);
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        MongoDBStrategyDAO mongoDBStrategyDAO = new MongoDBStrategyDAO(mongo);
        List<Strategy> strategies = mongoDBStrategyDAO.getUserStrategies(currUser.getId());
        return Response.status(Response.Status.OK).entity(strategies).build();
    }

    private User getAuthUser(HttpServletRequest request) throws ParseException, JOSEException {
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        MongoDBUserDAO mongoDBUserDAO = new MongoDBUserDAO(mongo);
        String subject = AuthUtils.getSubject(request.getHeader(AuthUtils.AUTH_HEADER_KEY));
        return mongoDBUserDAO.getUserByMail(subject);
    }
}

