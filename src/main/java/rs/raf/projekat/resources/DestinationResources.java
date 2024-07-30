package rs.raf.projekat.resources;

import rs.raf.projekat.entities.Destination;
import rs.raf.projekat.services.DestinationService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/destination")
public class DestinationResources {

    @Inject
    private DestinationService destinationService;

    @GET
    @Path("/all")
    @Produces("application/json")
    public Response getAllDestinations() {
        System.out.println("Destinacije ucitane");
        return Response.ok(destinationService.getAllDestinations()).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getDestinationById(@PathParam("id") Integer id) {
        System.out.println("Destinacija "+id+" ucitana");
        return Response.ok(destinationService.getDestinationById(id)).build();
    }

    @POST
    @Path("/add")
    @Produces("application/json")
    public Response addDestination(Destination destination) {
        System.out.println("Destinacija "+destination+" dodata");
        return Response.ok(destinationService.createDestination(destination)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteDestination(@PathParam("id") Integer id) {
        System.out.println("Destinacija "+id+" obrisana");
        destinationService.deleteDestination(id);
        return Response.ok().build();
    }

    @PUT
    @Path("/update/{id}")
    @Produces("application/json")
    public Response updateDestination(@PathParam("id") Integer id, @Valid Destination destination) {

        Response a = Response.ok(destinationService.updateDestination(destination,id)).build();
        System.out.println(a.toString() + " AAAAAAAAAAAAAAAAAAAAAAAAA");
        return a;
    }
}
