package fr.unice.polytech.soa1.TeamForce.Vivant.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface Catalogs {
/*
    @POST
    @Path("/")
    public Response create(@QueryParam("products") List<String> products, @QueryParam("id") String id);
*/
    @GET
    @Path("/")
    public Response getAllCatalogs(@QueryParam("query") String query);

    @GET
    @Path("/{id}")
    Response getCatalog(@PathParam("id") String id);


/*
    @PUT
    @Path("/{id}")
    Response modifyCatalog(@PathParam("id") String id, @PathParam("add") List<String> add, @PathParam("remove") List<String> remove);

    @DELETE
    @Path("/{id}")
    Response deleteCatalog(@PathParam("id") String id);
*/

}
