package spring.cloud.samples.security.role.hierarchy.controller;

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

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    String admin() {
        return "is Admin";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    String user() {
        return "is user";
    }

}
