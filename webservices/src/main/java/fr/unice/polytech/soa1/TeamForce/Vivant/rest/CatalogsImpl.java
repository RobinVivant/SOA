package fr.unice.polytech.soa1.TeamForce.Vivant.rest;

import fr.unice.polytech.soa1.TeamForce.Vivant.ResponseBuilder;
import fr.unice.polytech.soa1.TeamForce.Vivant.dao.CustomerDAO;

import javax.ejb.EJB;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.lang.reflect.Method;

@Path("/vivant/catalogs")
public class CatalogsImpl implements Catalogs {

    @EJB
    ResponseBuilder builder;

    @EJB
    CustomerDAO Cdao;

    @Override
    public Response getAllCatalogs(String query) {

        if( query == null )
            return Response.status(Response.Status.BAD_REQUEST).build();

        String[] qSplit = query.split("=");

        try {
            Method m = Cdao.getClass().getDeclaredMethod("find"+ qSplit[0]+"ById", new Class[]{String.class});
            return builder.fromOpt((java.util.Optional<?>) m.invoke(Cdao, qSplit[1]));
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Override
    public Response getCatalog(String id) {
        try {

            System.out.println("A");
            return builder.fromOpt(Cdao.findCatalogueById(id));
        }catch( Exception e){

            System.out.println("A");
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
