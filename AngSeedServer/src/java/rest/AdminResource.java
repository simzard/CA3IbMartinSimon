package rest;

import com.google.gson.Gson;
import entity.User;
import facades.UserFacade;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("admin")
//@RolesAllowed("Admin")
public class AdminResource {

    UserFacade facade = new UserFacade();
    
    @DELETE
    @Path("user/{id}")
    @Produces(MediaType.APPLICATION_JSON) 
    public String deleteUser(@PathParam("id") String id) {
        if (facade.deleteUser(id)) {
            return "{\"message\": \"user deleted\"}";
        }
        
        return "{\"message\": \"user NOT deleted\"}";
    }
    
    @GET
    @Path("users")
    @Produces(MediaType.APPLICATION_JSON) 
    public String getUsers() {
        List<User> users = facade.getUsers();
        
        return new Gson().toJson(users);
                
    }
   

    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSomething() {
        String now = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(new Date());
        return "{\"message\" : \"This message was delivered via a REST call accesible by only authenticated ADMINS\",\n"
                + "\"serverTime\": \"" + now + "\"}";
    }

}
