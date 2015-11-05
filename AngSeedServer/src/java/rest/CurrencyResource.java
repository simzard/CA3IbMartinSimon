package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import facades.ExchangeRateFacade;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.Currency;

@Path("currency")
@RolesAllowed("User")
public class CurrencyResource {

    ExchangeRateFacade facade = new ExchangeRateFacade();

    @GET
    @Path("dailyrate/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getValutaById(@PathParam("id") String stringId) {

        int valutaId = -1;

        try {
            valutaId = Integer.parseInt(stringId);
        } catch (NumberFormatException e) {
//            throw new CurrencyNotFoundException(("That CurrencyCode does not exists"));
        }

        Currency c = facade.getCurrency();

        JsonObject outJSON = new JsonObject();

        outJSON.addProperty("Date", c.getDate);
        outJSON.addProperty("Code", c.getCode);
        outJSON.addProperty("Desc", c.getDesc);
        outJSON.addProperty("Value", c.getValue);

        String jsonResponse = new Gson().toJson(outJSON);
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(jsonResponse);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJsonString = gson.toJson(je);

        return prettyJsonString;
    }

    @GET
    @Path("dailyrate/")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllValuta() {

        Currency cur = new Currency();

        JsonArray ja = new JsonArray();

        List<Currency> CurrencyList = facade.getAllCurrency();

        for (Currency c : CurrencyList) {

            JsonObject thisJSON = new JsonObject();
            thisJSON.addProperty("Date", c.getDate);
            thisJSON.addProperty("Code", c.getCode);
            thisJSON.addProperty("Desc", c.getDesc);
            thisJSON.addProperty("Value", c.getValue);
            ja.add(thisJSON);

        }

        String jsonResponse = new Gson().toJson(ja);
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(jsonResponse);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJsonString = gson.toJson(je);

        return prettyJsonString;
    }
}