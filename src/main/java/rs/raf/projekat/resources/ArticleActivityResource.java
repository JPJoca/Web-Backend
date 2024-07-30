package rs.raf.projekat.resources;


import rs.raf.projekat.entities.Activity;
import rs.raf.projekat.entities.Article;
import rs.raf.projekat.services.ArticleActivityService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/article_activity")
public class ArticleActivityResource {

    @Inject
    private ArticleActivityService articleActivityService;

    @GET
    @Path("/{articleId}")
    @Produces("application/json")
    public Response getActivitiesForArticle(@PathParam("articleId") Integer articleId) {
        List<Activity> activities = articleActivityService.getActivitiesForArticle(articleId);
        return Response.ok(activities).build();
    }

    @GET
    @Path("/activity/{activityId}")
    @Produces("application/json")
    public Response getArticlesForActivity(@PathParam("activityId") Integer activityId) {
        List<Article> articles = articleActivityService.getArticlesForActivity(activityId);
        return Response.ok(articles).build();
    }

    @POST
    @Path("/{article}/{activity}")
    @Produces("application/json")
    public Response addArticleActivity(@PathParam("article")Integer article_id,@PathParam("activity") Integer activity_id) {
        this.articleActivityService.addArticleActivity(article_id, activity_id);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{article}/{activity}")
    @Produces("application/json")
    public Response deleteArticle(@PathParam("article")Integer article_id,@PathParam("activity") Integer activity_id) {
        this.articleActivityService.deleteArticleActivity(article_id, activity_id);
        return Response.ok().build();
    }

}