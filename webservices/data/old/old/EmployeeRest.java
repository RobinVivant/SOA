package fr.unice.polytech.soa1.TeamForce.Vivant.rest.old;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Produces({"text/json"})
public interface EmployeeRest {
	
	@Path("/customer/{id}")
	@GET
	public Response customerInfo(@PathParam("id") String id);
	
	@Path("/customer/{id}/order/{OrderId}")
	@POST
	public Response addToOrder(@PathParam("id") String id, @PathParam("OrderId") String OrderId, @QueryParam("good") String good);

	
}
