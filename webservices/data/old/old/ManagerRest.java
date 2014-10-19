package fr.unice.polytech.soa1.TeamForce.Vivant.rest.old;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Produces({"text/json"})
public interface ManagerRest {

	@Path("/catalogue/{id}")
	@POST
	public Response addToCatalogue(@PathParam("id") String id, @QueryParam("catalogue") String catalogue);
	
	@Path("/catalogue/{id}")
	@GET
	public Response getCatalogue(@PathParam("id") String id);

	@Path("/catalogue")
	@POST
	public Response createCatalogue(@PathParam("id") String id, @QueryParam("catalogue") String catalogue);
	
	
}
