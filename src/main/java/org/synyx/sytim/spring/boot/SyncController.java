package org.synyx.sytim.spring.boot;

import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SyncController {

    // @Autowired
    // ProjectRepository repo;
    @RequestMapping(value = "/sync", method = POST, headers = {"Content-type=application/json"})
    public String index(@RequestParam(value = "ident", required = true) String ident,
            @RequestParam(value = "content", required = true) String content) {

        System.out.println("Content: " + content);

        System.out.println("Ident: " + ident);

        return "Greetings from Spring Boot Sytim Sync! " + ident + " " + content;
    }
}
