package sping.cloud.samples.oauth2.sso.resource;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableOAuth2Sso
@RestController
public class SampleController {

    @RequestMapping({"", "/"})
    String home() {
        return "Hello World";
    }

}
