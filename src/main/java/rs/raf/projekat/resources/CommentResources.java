package rs.raf.projekat.resources;

import rs.raf.projekat.entities.Comment;
import rs.raf.projekat.repositories.comment.CommentRepository;
import rs.raf.projekat.services.CommentServices;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
@Path("/comments")
public class CommentResources {

    @Inject
    private CommentServices commentService;

    @GET
    @Path("/{articleid}")
    @Produces("application/json")
    public Response allCommentsForArticle(@PathParam("articleid") Integer articleId){
        return Response.ok(this.commentService.allCommentsForArticle(articleId)).build();
    }


    @POST
    @Path("/addcomment")
    @Produces("application/json")
    public Comment addComment(@Valid Comment comment){
        return this.commentService.addComment(comment);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteArticle(@PathParam("id") Integer id) {
        this.commentService.deleteComment(id);
        return Response.ok().build();
    }

    @DELETE
    @Path("/deletecommentsforarticle/{articleId}")
    public Response deleteCommentsForArticle(@PathParam("articleId") Integer articleId) {
        this.commentService.deleteCommentsForArticle(articleId);
        return Response.ok().build();
    }
}
