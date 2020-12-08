package spring.cloud.samples.oauth2.jwt.keypair.uri.resource.controller;

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

    @GetMapping("/read")
    @PreAuthorize("#oauth2.hasScope('read')")
    String read() {
        return "Allow Read";
    }

    @GetMapping("/create")
    @PreAuthorize("#oauth2.hasScope('create')")
    String create() {
        return "Allow Create";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    String admin() {
        return "is Admin";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    String user() {
        return "is User";
    }

}
