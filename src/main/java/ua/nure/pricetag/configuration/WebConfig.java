package ua.nure.pricetag.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan({
        "ua.nure.pricetag.controller",
        "ua.nure.pricetag.entity",
        "ua.nure.pricetag.service",
        "ua.nure.pricetag.repository"})
@EnableJpaRepositories(basePackages = {"ua.nure.pricetag.repository"})
public class WebConfig extends WebMvcConfigurerAdapter {



}
