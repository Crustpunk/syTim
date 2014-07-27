package org.synyx.samples.spring.boot;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.synyx.samples.spring.boot.repo.ProjectRepository;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
public class SyncController {

    @Autowired
    ProjectRepository repo;

    @RequestMapping(value = "/sync", method = POST)
    public String index(@RequestParam(value = "ident", required = true) String ident,
        @RequestParam(value = "content", required = true) String content) {

        System.out.println("Content: " + content);
        System.out.println("Ident: " + ident);

        return "Greetings from Spring Boot Sytim Sync! " + ident + " " + content;
    }
}
