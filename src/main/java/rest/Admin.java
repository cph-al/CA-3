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

@Path("admin")
@RolesAllowed("Admin")
public class Admin
{
    private static final Gson gson = new Gson();
    private UserFacade facade = new UserFacade(Persistence.createEntityManagerFactory("pu_development"));
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSomething() {
        String now = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(new Date());
        return "{\"message\" : \"REST call accesible by only authenticated ADMINS\",\n" + "\"serverTime\": \"" + now + "\"}";
    }
    
    @Path("/users")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllUsers()
    {
        return gson.toJson(facade.getAllUsers());
    }

    @Path("/user/{username}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String deleteUser(@PathParam("username") String user)
    {
        facade.deleteUser(user);
        return "Deleted";
    }
}
