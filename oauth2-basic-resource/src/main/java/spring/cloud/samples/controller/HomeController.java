package spring.cloud.samples.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping({"", "/"})
    @PreAuthorize("isAuthenticated()")
    String index() {
        return "Welcome Home";
    }

}
