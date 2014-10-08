package webservices;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Path("/catalog")
public class CatalogImpl implements Catalog {

    private String buildJson(Object o){
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = null;
        try {
            return ow.writeValueAsString(o);
        } catch (IOException e) {
            return e.toString();
        }
    }


    @Override
    public Response create(List<String> products, String id) {
/*
        JSONArray o = null;
        try {
            o = new JSONArray(products);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("CACA " +o.toString());
*/
        //TODO : accéder aux products...
        System.out.println(products.size());

        return Response.ok(buildJson(new Object(){
            public int id = 0;
            public String[] products = {
                "H5Y5RE7NS43RW4H",
                "U5A3HL6EG33ZH2K"
            };
        })).build();
    }

    @Override
    public Response list() {
        return Response.ok(buildJson(new Object(){
            public String[] catalogs = {
                "WINTER14",
                "SUMMER15",
                "FALL14"
            };
        })).build();
    }

    @Override
    public Response getCatalog(String id) {
        return Response.ok(buildJson(new Object() {
            public String id = "WINTER2015";
            public String[] products = {
                "H5Y5RE7NS43RW4H",
                "U5A3HL6EG33ZH2K",
                "I6T5GG9OQ18BN1P",
                "H6W5SI7KX91WE5F",
                "W3F6ZN4MQ76SJ6Q",
                "X8Q1KZ4GG39JB7X",
                "V1L6TB8RQ74LN3F"
            };
        })).build();
    }

    @Override
    public Response modifyCatalog(String id, List<String> add, List<String> remove) {
        return Response.ok().build();
    }

    @Override
    public Response deleteCatalog(String id) {
        return Response.ok().build();
    }
}
