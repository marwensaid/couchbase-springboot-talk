package demo;

import com.couchbase.client.protocol.views.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.event.ValidatingCouchbaseEventListener;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.Page;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Service;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Laurent Doguin
 * @author Josh Long
 */
@SpringBootApplication
@EnableScheduling
public class Application {

    @Bean
    LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    ValidatingCouchbaseEventListener validationEventListener() {
        return new ValidatingCouchbaseEventListener(validator());
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    Facebook facebook(@Value("${facebook.accessToken}") String accessToken) {
        return new FacebookTemplate(accessToken);
    }

    @Bean
    CommandLineRunner commandLineRunner(PlaceRepository placeRepository,
                                        PlaceService placeService) {
        return args -> {

            Arrays.asList("Starbucks", "Philz Coffee").forEach(
                    query -> placeService.search(query, 37.752494, -122.414166, 5280));

            System.out.println("------------------------");
            System.out.println("query:findAll");
            placeRepository.findAll().forEach(System.out::println);

            System.out.println("------------------------");
            System.out.println("query:count");
            System.out.println(placeRepository.count());

            System.out.println("------------------------");
            System.out.println("query:byName");
            Query query = new Query();
            query.setKey("Philz Coffee");
            placeRepository.findByName(query).forEach(System.out::println);

        };
    }

}


