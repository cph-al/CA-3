package rest;

import com.google.gson.Gson;
import facades.UserFacade;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.security.RolesAllowed;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import entity.User;

@Path("admin")
@RolesAllowed("Admin")
public class Admin
{
    private static final Gson gson = new Gson();
    private UserFacade facade = new UserFacade(Persistence.createEntityManagerFactory("pu_development"));
    
    @Path("/users")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllUsers()
    {
        return gson.toJson(facade.getAllUsers());
    }

    @Path("/user/{username}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public User deleteUser(@PathParam("username") String username)
    {
        User u = facade.deleteUser(username);
        return u;
    }
}
