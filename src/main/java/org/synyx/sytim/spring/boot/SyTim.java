package org.synyx.sytim.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.rest.SpringRestGraphDatabase;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

@Configuration
@EnableAutoConfiguration
@EnableNeo4jRepositories(basePackages = "org.synyx.samples.spring.boot.repo")
@Import(RepositoryRestMvcConfiguration.class)
@ComponentScan
public class SyTim extends Neo4jConfiguration {

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(SyTim.class, args);
    }

    @Bean
    SpringRestGraphDatabase graphDatabaseService() {

        return new SpringRestGraphDatabase("http://localhost:7474/db/data");
    }
}
