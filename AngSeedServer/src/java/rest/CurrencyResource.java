package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Currency;
import facades.ExchangeRateFacade;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("currency")
//@RolesAllowed("User")
public class CurrencyResource {

    ExchangeRateFacade facade = new ExchangeRateFacade();

//    @GET
//    @Path("dailyrate/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public String getValutaById(@PathParam("id") String stringId) throws CurrencyNotFoundException {
//
//        int valutaId = -1;
//
//        try {
//            valutaId = Integer.parseInt(stringId);
//        } catch (NumberFormatException e) {
//            throw new CurrencyNotFoundException(("That CurrencyCode does not exists"));
//        }
//
//        Currency c = facade.getCurrency();
//        
//         if (c == null) {
//            throw new CurrencyNotFoundException("That CurrencyCode does not exists");
//        }
//
//        JsonObject outJSON = new JsonObject();
//
//        outJSON.addProperty("Date", c.getDate);
//        outJSON.addProperty("Code", c.getCode);
//        outJSON.addProperty("Desc", c.getDesc);
//        outJSON.addProperty("Value", c.getValue);
//
//        String jsonResponse = new Gson().toJson(outJSON);
//        JsonParser jp = new JsonParser();
//        JsonElement je = jp.parse(jsonResponse);
//
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        String prettyJsonString = gson.toJson(je);
//
//        return prettyJsonString;
//    }

    @GET
    @Path("dailyrate")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllValuta() {

        JsonArray ja = new JsonArray();

        List<Currency> CurrencyList = facade.getDailyRates();

        for (Currency c : CurrencyList) {

            JsonObject thisJSON = new JsonObject();
            thisJSON.addProperty("Date", c.getDate());
            thisJSON.addProperty("Code", c.getCode());
            thisJSON.addProperty("Desc", c.getDescribtion());
            thisJSON.addProperty("Value", c.getRate());
            ja.add(thisJSON);

        }

        String jsonResponse = new Gson().toJson(ja);
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(jsonResponse);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJsonString = gson.toJson(je);

        return prettyJsonString;
    }

    @GET
    @Path("calculator/{amount}/{fromCurrency}/{toCurrency}")
    @Produces(MediaType.TEXT_PLAIN)

     //:amount/:from currency/:tocurrency
    public float calculate(
            @PathParam("amount") float amount,
            @PathParam("fromCurrency") String fromCurrency,
            @PathParam("toCurrency") String toCurrency
    ) {
            
        return facade.convertCurrency(amount, fromCurrency, toCurrency);
       
    }

}
