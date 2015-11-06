/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

import com.google.gson.JsonObject;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author simon
 */
@Provider
public class CurrencyNotFoundExceptionMapper implements ExceptionMapper<CurrencyNotFoundException> {

    @Context
    ServletContext context;
    
    @Override
    public Response toResponse(CurrencyNotFoundException e) {
        JsonObject jo = new JsonObject();
        String isDebug = context.getInitParameter("debug");
        if (Boolean.valueOf(context.getInitParameter("debug"))) {
            jo.addProperty("StackTrace", e.getStackTrace().toString());
        }
        jo.addProperty("code", "404");
        jo.addProperty("message", e.getMessage());
        return Response.status(Response.Status.NOT_FOUND).entity(jo.toString()).build();
    }
    
}
