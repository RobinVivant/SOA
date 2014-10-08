package webservices;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public interface Catalog {

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@QueryParam("products") List<String> products, @QueryParam("id") String id);

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getCatalog(@PathParam("id")String id);

    @PUT
    @Path("/{id}")
    Response modifyCatalog(@PathParam("id")String id, @PathParam("add")List<String> add, @PathParam("remove")List<String> remove);

    @DELETE
    @Path("/{id}")
    Response deleteCatalog(@PathParam("id")String id);
}