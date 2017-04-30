package ua.nure.pricetag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.nure.pricetag.configuration.JPAConfiguration;
import ua.nure.pricetag.configuration.WebConfig;

@SpringBootApplication
@ImportAutoConfiguration(classes = {JPAConfiguration.class, WebConfig.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
