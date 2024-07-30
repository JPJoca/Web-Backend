package rs.raf.projekat.resources;

import rs.raf.projekat.entities.Article;
import rs.raf.projekat.entities.Destination;
import rs.raf.projekat.services.ArticleService;
import rs.raf.projekat.services.DestinationService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;

@Path("/article")
public class ArticleResource {
    @Inject
    private ArticleService articleService;

    @GET
    @Path("/all")
    @Produces("application/json")
    public Response getAllArticles() {
        System.out.println("Artikli ucitani");
        return Response.ok(articleService.getAllArticles()).build();
    }

    @GET
    @Path("/viewed")
    @Produces("application/json")
    public Response getMostViewedArticles() {
        Timestamp date = new Timestamp(System.currentTimeMillis());
        System.out.println("Artikli sa vremenom " + date + " ucitani");
        return Response.ok(articleService.getMostViewdArticle(date)).build();
    }
    @GET
    @Path("/all/{id}")
    @Produces("application/json")
    public Response getArticleDestinationById(@PathParam("id") Integer id) {
        System.out.println("Artikli "+id+" ucitani");
        return Response.ok(articleService.getAllDestinationArticles(id)).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getArticleById(@PathParam("id") Integer id) {
        System.out.println("Artikal "+id+" ucitan");
        return Response.ok(articleService.getArticleById(id)).build();
    }

    @POST
    @Path("/add")
    @Produces("application/json")
    public Response addArticle(Article article) {
        System.out.println("Artikal  "+article+" dodat");
        return Response.ok(articleService.createArticle(article)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteArticle(@PathParam("id") Integer id) {
        System.out.println("Artikal  "+id+" obrisan");
        articleService.deleteArticle(id);
        return Response.ok().build();
    }

    @PUT
    @Path("/update/{id}")
    @Produces("application/json")
    public Response updateDestination(@PathParam("id") Integer id, @Valid Article article) {
        System.out.println("Destinacija "+id+" apdejtovana");
        return  Response.ok(articleService.updateArticle(article,id)).build();


    }
}
