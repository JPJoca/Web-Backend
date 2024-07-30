package rs.raf.projekat.resources;


import rs.raf.projekat.entities.Activity;
import rs.raf.projekat.services.ActivityService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/activities")
public class ActivityResource {

    @Inject
    private ActivityService activityService;

    @GET
    @Produces("application/json")
    public Response allActivities(){
        return Response.ok(this.activityService.allActivities()).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response findActivityById(@PathParam("id") Integer id){
        return Response.ok(this.activityService.findActivityById(id)).build();
    }

    @POST
    @Path("/add")
    @Produces("application/json")
    public Activity addActivity(@Valid Activity activity){
        return this.activityService.addActivity(activity);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteActivity(@PathParam("id") Integer id) {
        this.activityService.deleteActivity(id);
        return Response.ok().build();
    }
}
