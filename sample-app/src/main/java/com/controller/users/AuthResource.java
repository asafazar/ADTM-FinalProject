package com.controller.users;

import com.DB.MongoDBUserDAO;
import com.controller.auth.AuthUtils;
import com.controller.auth.PasswordService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
@Path("/auth")
public class AuthResource {

    public static final String CLIENT_ID_KEY = "client_id", REDIRECT_URI_KEY = "redirect_uri",
            CLIENT_SECRET = "client_secret", CODE_KEY = "code", GRANT_TYPE_KEY = "grant_type",
            AUTH_CODE = "authorization_code";

    public static final String CONFLICT_MSG = "There is already a %s account that belongs to you",
            NOT_FOUND_MSG = "User not found", LOGING_ERROR_MSG = "Wrong email and/or password",
            UNLINK_ERROR_MSG = "Could not unlink %s account because it is your only sign-in method";

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
                    final Token token = AuthUtils.createToken(request.getRemoteHost(), checkUser.getId());
                    return Response.ok().entity(token).build();
                } catch (JOSEException e) {
                    e.printStackTrace();
                    return Response.status(Response.Status.UNAUTHORIZED).entity(new Error(LOGING_ERROR_MSG)).build();
                }
            } else {
                return Response.status(Status.UNAUTHORIZED).entity(null).build();
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
        signUpUser.setPassword(PasswordService.hashPassword(signUpUser.getPassword()));
        final User savedUser = mongoDBUserDAO.save(signUpUser);
        final Token token = AuthUtils.createToken(request.getRemoteHost(), savedUser.getId());
        return Response.status(Status.CREATED).type(MediaType.APPLICATION_JSON_TYPE).entity(token).build();
    }
}
/*
  @POST
  @Path("facebook")
  @UnitOfWork
  @Consumes(MediaType.APPLICATION_JSON)
  public Response loginFacebook(@Valid final Payload payload,
      @Context final HttpServletRequest request) throws JsonParseException, JsonMappingException,
      IOException, ParseException, JOSEException {
    final String accessTokenUrl = "https://graph.facebook.com/v2.3/oauth/access_token";
    final String graphApiUrl = "https://graph.facebook.com/v2.3/me";

    Response response;

    // Step 1. Exchange authorization code for access token.

    response =
        client.target(accessTokenUrl).queryParam(CLIENT_ID_KEY, payload.getClientId())
            .queryParam(REDIRECT_URI_KEY, payload.getRedirectUri())
            .queryParam(CLIENT_SECRET, secrets.getFacebook())
            .queryParam(CODE_KEY, payload.getCode()).request("text/plain")
            .accept(MediaType.TEXT_PLAIN).get();

    Map<String, Object> responseEntity = getResponseEntity(response);
        
    response =
            client.target(graphApiUrl).queryParam("access_token", responseEntity.get("access_token"))
                .queryParam("expires_in", responseEntity.get("expires_in")).request("text/plain").get();
    
    final Map<String, Object> userInfo = getResponseEntity(response);

    // Step 3. Process the authenticated the user.
    return processUser(request, Provider.FACEBOOK, userInfo.get("id").toString(),
        userInfo.get("name").toString());
  }


  @POST
  @Path("google")
  @UnitOfWork
  public Response loginGoogle(@Valid final Payload payload,
      @Context final HttpServletRequest request) throws JOSEException, ParseException,
      JsonParseException, JsonMappingException, IOException {
    final String accessTokenUrl = "https://accounts.google.com/o/oauth2/token";
    final String peopleApiUrl = "https://www.googleapis.com/plus/v1/people/me/openIdConnect";
    Response response;

    // Step 1. Exchange authorization code for access token.
    final MultivaluedMap<String, String> accessData = new MultivaluedHashMap<String, String>();
    accessData.add(CLIENT_ID_KEY, payload.getClientId());
    accessData.add(REDIRECT_URI_KEY, payload.getRedirectUri());
    accessData.add(CLIENT_SECRET, secrets.getGoogle());
    accessData.add(CODE_KEY, payload.getCode());
    accessData.add(GRANT_TYPE_KEY, AUTH_CODE);
    response = client.target(accessTokenUrl).request().post(Entity.form(accessData));
    accessData.clear();

    // Step 2. Retrieve profile information about the current user.
    final String accessToken = (String) getResponseEntity(response).get("access_token");
    response =
        client.target(peopleApiUrl).request("text/plain")
            .header(AuthUtils.AUTH_HEADER_KEY, String.format("Bearer %s", accessToken)).get();
    final Map<String, Object> userInfo = getResponseEntity(response);

    // Step 3. Process the authenticated the user.
    return processUser(request, Provider.GOOGLE, userInfo.get("sub").toString(),
        userInfo.get("name").toString());
  }

  @POST
  @Path("linkedin")
  @UnitOfWork
  public Response loginLinkedin() {
    return Response.ok().build();
  }

  @POST
  @Path("github")
  @UnitOfWork
  public Response loginGithub() {
    return Response.ok().build();
  }

  @POST
  @Path("foursquare")
  @UnitOfWork
  public Response loginFoursquare() {
    return Response.ok().build();
  }

  @GET
  @Path("twitter")
  @UnitOfWork
  public Response loginTwitter(@Context final HttpServletRequest request) {
    return Response.ok().build();
  }


  @POST
  @Path("unlink/")
  @UnitOfWork
  public Response unlink(@Valid final UnlinkRequest unlinkRequest,
      @Context final HttpServletRequest request) throws ParseException, IllegalArgumentException,
      IllegalAccessException, NoSuchFieldException, SecurityException, JOSEException {
    final String subject = AuthUtils.getSubject(request.getHeader(AuthUtils.AUTH_HEADER_KEY));
    final Optional<User> foundUser = dao.findById(Long.parseLong(subject));

    String provider = unlinkRequest.provider;
    
    if (!foundUser.isPresent()) {
      return Response.status(Status.NOT_FOUND).entity(new ErrorMessage(NOT_FOUND_MSG)).build();
    }

    final User userToUnlink = foundUser.get();

   // check that the user is not trying to unlink the only sign-in method
    if (userToUnlink.getSignInMethodCount() == 1) {
      return Response.status(Status.BAD_REQUEST)
          .entity(new ErrorMessage(String.format(UNLINK_ERROR_MSG, provider))).build();
    }

    try {
      userToUnlink.setProviderId(Provider.valueOf(provider.toUpperCase()), null);
    } catch (final IllegalArgumentException e) {
      return Response.status(Status.BAD_REQUEST).build();
    }

    dao.save(userToUnlink);

    return Response.ok().build();
  }

  
  public static class UnlinkRequest {
	  @NotBlank
	  String provider;

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}
	  
  }

  *//*
*/
/*
*//*

*/
/*
   * Inner classes for entity wrappers
   *//*
*/
/*
*//*

*/
/*
  public static class Payload {
    @NotBlank
    String clientId;

    @NotBlank
    String redirectUri;

    @NotBlank
    String code;

    public String getClientId() {
      return clientId;
    }

    public String getRedirectUri() {
      return redirectUri;
    }

    public String getCode() {
      return code;
    }
  }

  *//*
*/
/*
*//*

*/
/*
   * Helper methods
   *//*
*/
/*
*//*

*/
/*
  private Map<String, Object> getResponseEntity(final Response response) throws JsonParseException,
      JsonMappingException, IOException {
    return MAPPER.readValue(response.readEntity(String.class),
        new TypeReference<Map<String, Object>>() {});
  }

  private Response processUser(final HttpServletRequest request, final Provider provider,
      final String id, final String displayName) throws JOSEException, ParseException {
    final Optional<User> user = dao.findByProvider(provider, id);

    // Step 3a. If user is already signed in then link accounts.
    User userToSave;
    final String authHeader = request.getHeader(AuthUtils.AUTH_HEADER_KEY);
    if (StringUtils.isNotBlank(authHeader)) {
      if (user.isPresent()) {
        return Response.status(Status.CONFLICT)
            .entity(new ErrorMessage(String.format(CONFLICT_MSG, provider.capitalize()))).build();
      }

      final String subject = AuthUtils.getSubject(authHeader);
      final Optional<User> foundUser = dao.findById(Long.parseLong(subject));
      if (!foundUser.isPresent()) {
        return Response.status(Status.NOT_FOUND).entity(new ErrorMessage(NOT_FOUND_MSG)).build();
      }

      userToSave = foundUser.get();
      userToSave.setProviderId(provider, id);
      if (userToSave.getDisplayName() == null) {
        userToSave.setDisplayName(displayName);
      }
      userToSave = dao.save(userToSave);
    } else {
      // Step 3b. Create a new user account or return an existing one.
      if (user.isPresent()) {
        userToSave = user.get();
      } else {
        userToSave = new User();
        userToSave.setProviderId(provider, id);
        userToSave.setDisplayName(displayName);
        userToSave = dao.save(userToSave);
      }
    }

    final Token token = AuthUtils.createToken(request.getRemoteHost(), userToSave.getId());
    return Response.ok().entity(token).build();
  }

}*/
