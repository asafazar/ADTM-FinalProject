package com.controller.actions;

import com.DB.MongoDBActionDAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.actions.AbstractAction;
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

@Path("/action")
public class ActionResource {

    public ActionResource() {
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(String action, @Context final HttpServletRequest request)
            throws JOSEException {
        Gson gson = new GsonBuilder().create();
        AbstractAction abstractAction = gson.fromJson(action, AbstractAction.class);
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        MongoDBActionDAO mongoDBActionDAO = new MongoDBActionDAO(mongo);
        AbstractAction newAction = mongoDBActionDAO.createAction(abstractAction);
        return Response.status(Response.Status.CREATED).entity(newAction).build();
    }

    @POST
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(String action, @Context final HttpServletRequest request)
            throws JOSEException {
        Gson gson = new GsonBuilder().create();
        AbstractAction abstractAction = gson.fromJson(action, AbstractAction.class);
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        MongoDBActionDAO mongoDBActionDAO = new MongoDBActionDAO(mongo);
        mongoDBActionDAO.deleteAction(abstractAction);
        return Response.status(Response.Status.OK).build();
    }


    @POST
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(String action, @Context final HttpServletRequest request)
            throws JOSEException {
        Gson gson = new GsonBuilder().create();
        AbstractAction abstractAction = gson.fromJson(action , AbstractAction.class);
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        MongoDBActionDAO mongoDBActionDAO= new MongoDBActionDAO(mongo);
        mongoDBActionDAO.updateAction(abstractAction);
        return Response.status(Response.Status.OK).build();
    }
}
