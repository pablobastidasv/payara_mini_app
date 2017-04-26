package co.pablobastidasv.boundary;

import co.pablobastidasv.control.MongoProducer;
import co.pablobastidasv.entity.Name;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.MessageFormat;
import java.util.logging.Logger;

@Path("")
@Produces(value = "application/json")
@Consumes(value = "application/json")
public class Resource {

    @Inject
    private MongoProducer mongoProducer;

    private static final Logger LOGGER = Logger.getLogger("resource");

    @POST
    public void create(Name name) throws UnknownHostException {

        String msg = MessageFormat.format("Posting from host {0}", InetAddress.getLocalHost().getHostName());
        LOGGER.warning(msg);

        mongoProducer.getDatastore().save(name);
    }

    @GET
    @Path("ping/{value}")
    public Response get(String value){
        return Response.ok(value).build();
    }

    @GET
    public JsonArray get() throws UnknownHostException {

        String msg = MessageFormat.format("Getting from host {0}", InetAddress.getLocalHost().getHostName());
        LOGGER.warning(msg);

        final JsonArrayBuilder builder = Json.createArrayBuilder();
        mongoProducer.getDatastore()
                .find(Name.class)
                .asList()
                .stream()
                .map(Name::toJson)
                .forEach(builder::add);
        return builder.build();
    }

}
