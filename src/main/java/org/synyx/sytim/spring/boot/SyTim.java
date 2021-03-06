package org.synyx.sytim.spring.boot;

import org.apache.commons.logging.Log;
import org.jboss.logging.Logger;
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
@EnableNeo4jRepositories(basePackages = "org.synyx.sytim.spring.boot")
@ComponentScan(basePackages = {"org.synyx.sytim.spring.boot"})
@Import(RepositoryRestMvcConfiguration.class)
public class SyTim extends Neo4jConfiguration {

    static Logger logger = Logger.getLogger(SyTim.class);

    public SyTim() {

        setBasePackage("org.synyx.sytim.spring.boot.domain"); // needed due a neo4j api change .. hopefully can be remoced in later versions
    }

    public static void main(String[] args) {

        logger.info("SyTim starting");

        ApplicationContext ctx = SpringApplication.run(SyTim.class, args);
    }

    @Bean
    SpringRestGraphDatabase graphDatabaseService() {

        return new SpringRestGraphDatabase("http://localhost:7474/db/data");
    }
}
