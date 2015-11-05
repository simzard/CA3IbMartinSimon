package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("demoCurrency")
@RolesAllowed("Admin")
public class CurrencyResource {

    @GET

    @Produces(MediaType.APPLICATION_JSON)
    public String getValutaById(@PathParam("id") String stringId) {

        int personId = -1;

        personId = Integer.parseInt(stringId);

        JsonObject outJSON = new JsonObject();
//        outJSON.addProperty("lastName", p.getLastName());

        JsonArray hobbiesArray = new JsonArray();

        outJSON.add("hobbies", hobbiesArray);

//        outJSON.addProperty("email", p.getEmail());

        String jsonResponse = new Gson().toJson(outJSON);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(jsonResponse);
        String prettyJsonString = gson.toJson(je);

        return prettyJsonString;

    }
}
