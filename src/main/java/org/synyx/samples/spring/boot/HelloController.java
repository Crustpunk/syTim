package org.synyx.samples.spring.boot;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class HelloController {

    @RequestMapping(value="/hello", method = GET)
    public String index() {
        return "Greetings from Spring Boot!";
    }

}
