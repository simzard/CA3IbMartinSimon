package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import exceptions.CurrencyNotFoundException;
import facades.ExchangeRateFacade;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("currency")
//@RolesAllowed("User")
public class CurrencyTest {
    
    @GET
    @Path("test/")
    @Produces(MediaType.APPLICATION_JSON)
    public void getValutaById() throws CurrencyNotFoundException {

        throw new CurrencyNotFoundException(("That CurrencyCode does not exist"));

    }   
}
