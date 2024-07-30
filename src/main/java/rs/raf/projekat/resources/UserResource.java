package rs.raf.projekat.resources;

import rs.raf.projekat.entities.User;
import rs.raf.projekat.requests.LoginRequest;
import rs.raf.projekat.services.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/users")
public class UserResource {

    @Inject
    private UserService userService;

    @POST
    @Path("/login")
    @Produces({MediaType.APPLICATION_JSON})
    public Response login(@Valid LoginRequest loginRequest)
    {
        Map<String, String> response = new HashMap<>();

        String jwt = this.userService.login(loginRequest.getEmail(), loginRequest.getPassword());

        if (jwt == null) {
            response.put("message", "These credentials do not match our records");
            return Response.status(422, "Unprocessable Entity").entity(response).build();
        }

        response.put("jwt", jwt);

        return Response.ok(response).build();
    }

    @GET
    @Produces("application/json")
    public Response allUsers(){
        return Response.ok(this.userService.allUsers()).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public User findById(@PathParam("id") Integer id){
        return this.userService.findUserById(id);
    }

    @POST
    @Path("/admin/adduser")
    @Produces("application/json")
    public User createUser(@Valid User user){
        return this.userService.addUser(user);
    }

    @PUT
    @Path("/admin/updatestatus/{id}")
    @Produces("application/json")
    public User updateUserStatus(@PathParam("id") Integer id){
        return this.userService.updateUserStatus(id);
    }

    @PUT
    @Path("/admin/updateuser/{id}")
    @Produces("application/json")
    public User updateUser(@PathParam("id") Integer id, @Valid User user){
        return this.userService.updateUser(user, id);
    }

}
