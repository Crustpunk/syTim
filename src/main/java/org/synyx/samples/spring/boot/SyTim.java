package org.synyx.samples.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;

import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.rest.SpringRestGraphDatabase;
//import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import java.util.Arrays;


@Configuration
@EnableAutoConfiguration
@EnableNeo4jRepositories(basePackages = "org.synyx.samples.spring.boot")

//@Import(RepositoryRestMvcConfiguration.class)
@ComponentScan
public class SyTim extends Neo4jConfiguration {

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(SyTim.class, args);

        System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);

        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }


    @Bean
    SpringRestGraphDatabase graphDatabaseService() {

        return new SpringRestGraphDatabase("http://localhost:7474/db/data");
    }
}
