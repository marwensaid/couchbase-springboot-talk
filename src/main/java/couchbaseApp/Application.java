package couchbaseApp;

import com.couchbase.client.protocol.views.Query;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.couchbase.core.mapping.event.ValidatingCouchbaseEventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Arrays;


//@SpringBootApplication
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


