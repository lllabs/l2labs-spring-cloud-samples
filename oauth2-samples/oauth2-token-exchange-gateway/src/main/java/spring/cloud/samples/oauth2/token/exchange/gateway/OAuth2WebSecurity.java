package spring.cloud.samples.oauth2.token.exchange.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class OAuth2WebSecurity {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        //@formatter:off
        http.csrf().disable();
//        http.formLogin().disable();
//        http.logout().disable();
//        http.authorizeExchange().anyExchange().authenticated();

        //@formatter:on1

        return http.build();
    }

}
