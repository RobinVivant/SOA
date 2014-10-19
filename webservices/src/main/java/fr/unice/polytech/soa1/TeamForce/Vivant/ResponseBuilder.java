package fr.unice.polytech.soa1.TeamForce.Vivant;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ejb.Singleton;
import javax.ws.rs.core.Response;
import java.util.Optional;


@Singleton(name = "Response-Builder")
public class ResponseBuilder {

    final Gson gson = new GsonBuilder().create();

    public Response fromOpt(Optional<?> opt) {
        if (opt.isPresent()) {
            final String json = gson.toJson(opt.get());
            return Response.ok(json).build();
        } else
            return Response.status(Response.Status.NOT_FOUND).build();
    }
}
