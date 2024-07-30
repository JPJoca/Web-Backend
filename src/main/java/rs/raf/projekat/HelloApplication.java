package rs.raf.projekat;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import rs.raf.projekat.repositories.activity.ActivityRepository;
import rs.raf.projekat.repositories.activity.MySqlActivityRepository;
import rs.raf.projekat.repositories.articleactivity.ArticleActivityRepository;
import rs.raf.projekat.repositories.articleactivity.MySqlArticleActivityRepository;
import rs.raf.projekat.repositories.articles.ArticleRepository;
import rs.raf.projekat.repositories.articles.MySqlArticleRepository;
import rs.raf.projekat.repositories.comment.CommentRepository;
import rs.raf.projekat.repositories.comment.MySQLCommentRepository;
import rs.raf.projekat.repositories.destination.DestinationRepository;
import rs.raf.projekat.repositories.destination.MySqlDestinationRepository;
import rs.raf.projekat.repositories.user.MySqlUserRepository;
import rs.raf.projekat.repositories.user.UserRepository;
import rs.raf.projekat.services.*;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class HelloApplication extends ResourceConfig {

    public HelloApplication() {
        // Ukljucujemo validaciju
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        // Definisemo implementacije u dependency container-u
        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
                this.bind(MySqlUserRepository.class).to(UserRepository.class).in(Singleton.class);
                this.bind(MySqlDestinationRepository.class).to(DestinationRepository.class).in(Singleton.class);
                this.bind(MySqlArticleRepository.class).to(ArticleRepository.class).in(Singleton.class);
                this.bind(MySQLCommentRepository.class).to(CommentRepository.class).in(Singleton.class);
                this.bind(MySqlActivityRepository.class).to(ActivityRepository.class).in(Singleton.class);
                this.bind(MySqlArticleActivityRepository.class).to(ArticleActivityRepository.class).in(Singleton.class);

                this.bindAsContract(UserService.class);
                this.bindAsContract(DestinationService.class);
                this.bindAsContract(ArticleService.class);
                this.bindAsContract(CommentServices.class);
                this.bindAsContract(ActivityService.class);
                this.bindAsContract(ArticleActivityService.class);

            }
        };
        register(binder);

        // Ucitavamo resurse
        packages("rs.raf.projekat");
    }
}
