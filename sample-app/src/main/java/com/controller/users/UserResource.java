package com.controller.users;

import com.DB.MongoDBUserDAO;
import com.controller.auth.AuthUtils;
import com.mongodb.MongoClient;
import com.nimbusds.jose.JOSEException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.text.ParseException;

@Path("/api/me")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

	public UserResource() {
	}

	@GET
	public Response getUser(@Context HttpServletRequest request) throws ParseException, JOSEException {
		User foundUser = getAuthUser(request);
		
		if (foundUser == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok().entity(foundUser).build();
	}

	@PUT
	public Response updateUser(@Valid User user, @Context HttpServletRequest request) throws ParseException, JOSEException {
		User foundUser = getAuthUser(request);
		
		if (foundUser == null) {
			return Response
					.status(Status.NOT_FOUND)
					.entity(new Error(AuthResource.NOT_FOUND_MSG)).build();
		}

        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        MongoDBUserDAO mongoDBUserDAO = new MongoDBUserDAO(mongo);
		User userToUpdate = foundUser;
		userToUpdate.setDisplayName(user.getDisplayName());
		userToUpdate.setEmail(user.getEmail());
        mongoDBUserDAO.save(userToUpdate);

		return Response.ok().build();
	}

	private User getAuthUser(HttpServletRequest request) throws ParseException, JOSEException {
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        MongoDBUserDAO mongoDBUserDAO = new MongoDBUserDAO(mongo);
        String subject = AuthUtils.getSubject(request.getHeader(AuthUtils.AUTH_HEADER_KEY));
		return mongoDBUserDAO.getUserByMail(subject);
	}

}
