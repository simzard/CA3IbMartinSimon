package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.User;
import facades.UserFacade;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("demouser")
//@RolesAllowed("User")
public class UserResource {

    UserFacade facade = new UserFacade();

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public String createUser(String userJSON) {
        //Get the person text from the provided Json
        JsonObject userInJSON = new JsonParser().parse(userJSON).getAsJsonObject();
        String username = userInJSON.get("username").getAsString();
        String password = userInJSON.get("password").getAsString();
        //JsonArray JsonRoles = userInJSON.get("roles").getAsJsonArray();

        System.out.println("username:" + username);
        System.out.println("password:" + password);
//        System.out.print("\n\nroles:");
//        for (int i = 0; i < JsonRoles.size(); i++) {
//            System.out.print(JsonRoles.get(i).toString());
//        }

        //JsonArray personPhones = new JsonArray();
        List<String> rolesList = new ArrayList();

//        for (int i = 0; i < JsonRoles.size(); i++) {
//            rolesList.add(JsonRoles.get(i).toString());
//        }
        List<String> TMPROLES = new ArrayList();
        TMPROLES.add("User");

        User newUser = new User(username, password, TMPROLES);

        if (facade.doesUserExist(username)) {

            return "{\"message\": \"User allready exists\"}";

        }

        facade.createUser(newUser);

        // out
//        JsonObject personOutJSON = new JsonObject();
//
//        personOutJSON.addProperty("id", newPerson.getId());
//        personOutJSON.addProperty("firstName", newPerson.getFirstName());
//        personOutJSON.addProperty("lastName", newPerson.getLastName());
//
//        personOutJSON.add("hobbies", personHobbies);
//        personOutJSON.add("phones", personPhones);
//        personOutJSON.addProperty("email", newPerson.getEmail());
//
//        String jsonResponse = new Gson().toJson(personOutJSON);
//
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        JsonParser jp = new JsonParser();
//        JsonElement je = jp.parse(jsonResponse);
//        String prettyJsonString = gson.toJson(je);
        return "{\"message\": \"User is created\"}";

    }

}
