package com.controller.users;

import com.DB.MongoDBUserDAO;
import com.controller.auth.AuthUtils;
import com.controller.auth.PasswordService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.user.User;
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
import javax.ws.rs.core.Response.Status;
import java.util.Random;

@Path("/auth")
public class AuthResource {

    public static final String CLIENT_ID_KEY = "client_id", REDIRECT_URI_KEY = "redirect_uri",
            CLIENT_SECRET = "client_secret", CODE_KEY = "code", GRANT_TYPE_KEY = "grant_type",
            AUTH_CODE = "authorization_code";

    public static final String CONFLICT_MSG = "There is already a %s account that belongs to you",
            NOT_FOUND_MSG = "User not found", LOGING_ERROR_MSG = "Wrong email and/or password",
            UNLINK_ERROR_MSG = "Could not unlink %s account because it is your only sign-in method",
            SIGN_UP_MSG = "Email already exist";

    public static final ObjectMapper MAPPER = new ObjectMapper();

    public AuthResource() {
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(String user, @Context final HttpServletRequest request)
            throws JOSEException {
        if (user == null) {
            System.out.println("Could nor persist work- null");
            return Response.status(Response.Status.UNAUTHORIZED).entity(null).build();
        } else {
            Gson gson = new GsonBuilder().create();
            User loginUser = gson.fromJson(user, User.class);
            MongoClient mongo = (MongoClient) request.getServletContext()
                    .getAttribute("MONGO_CLIENT");
            MongoDBUserDAO mongoDBUserDAO = new MongoDBUserDAO(mongo);
            User checkUser = mongoDBUserDAO.getUserByMail(loginUser.getEmail());
            if (checkUser != null
                    && PasswordService.checkPassword(loginUser.getPassword(), checkUser.getPassword())) {
                try {
                    final Token token = AuthUtils.createToken(request.getRemoteHost(), new Random().nextLong());
                    return Response.ok().entity(token).build();
                } catch (JOSEException e) {
                    e.printStackTrace();
                    return Response.status(Response.Status.UNAUTHORIZED).entity(LOGING_ERROR_MSG).build();
                }
            } else {
                return Response.status(Status.UNAUTHORIZED).entity(new Error(LOGING_ERROR_MSG)).build();
            }
        }
    }

    @POST
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response signup(String user, @Context final HttpServletRequest request)
            throws JOSEException {
        Gson gson = new GsonBuilder().create();
        User signUpUser = gson.fromJson(user, User.class);
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        MongoDBUserDAO mongoDBUserDAO = new MongoDBUserDAO(mongo);

        if (mongoDBUserDAO.getUserByMail(signUpUser.getEmail()) == null) {
            signUpUser.setPassword(PasswordService.hashPassword(signUpUser.getPassword()));
            final User savedUser = mongoDBUserDAO.save(signUpUser);
            final Token token = AuthUtils.createToken(request.getRemoteHost(), new Random().nextLong());
            return Response.status(Status.CREATED).entity(token).build();
        }
        else
        {
            return Response.status(Status.UNAUTHORIZED).entity(SIGN_UP_MSG).build();
        }
    }
}