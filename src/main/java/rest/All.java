package rest;

import com.google.gson.Gson;
import facades.UserFacade;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import entity.User;

@Path("demoall")
public class All
{
    private static final Gson gson = new Gson();
    private UserFacade facade = new UserFacade(Persistence.createEntityManagerFactory("pu_development"));
    
    @Context
    private UriInfo context;

    public All()
    {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getText()
    {
        System.out.println("XXXXXXXX---> " + System.getProperty("java.version"));
        return " {\"message\" : \"result for all\"}";
    }

    @Path("/user")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String createUser(String user)
    {
        User u = (User)gson.fromJson(user, User.class);
        System.out.println(u.getUserName() +" - "+ u.getPassword());
        facade.createUser(u.getUserName(), u.getPassword());
        return "WORKS";
    }

}
