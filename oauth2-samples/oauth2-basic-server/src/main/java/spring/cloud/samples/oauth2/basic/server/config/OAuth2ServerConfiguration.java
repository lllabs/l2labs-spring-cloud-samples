package spring.cloud.samples.oauth2.basic.server.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;

@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
public class OAuth2ServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;

    /**
     * endpoint 에 대한 접근 권한 설정.
     * /oauth/authorize         : OAuth Login Form & Redirect
     * /oauth/confirm_access    :
     * /oauth/error             :
     * POST: /oauth/token       : access token or JWT 발급.   Default Basic Auth.
     * POST: /oauth/check_token : access token 으로 User 조회. Default denyAll()     set checkTokenAccess("denyAll()"|"isAuthenticated()"|"permitAll()")
     * POST: /oauth/token_key   : JWT 토큰의 공개 키 조회.        Default denyAll()     set tokenKeyAccess( ... )
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("isAuthenticated()");
    }

    /**
     * Configure the {@link ClientDetailsService}, e.g. declaring individual clients and their properties. Note that
     * password grant is not enabled (even if some clients are allowed it) unless an {@link AuthenticationManager} is
     * supplied to the {@link #configure(AuthorizationServerEndpointsConfigurer)}. At least one client, or a fully
     * formed custom {@link ClientDetailsService} must be declared or the server will not start.
     *
     * @param clients the client details configurer
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client-id")
                .secret("{noop}client-secret")
                .authorizedGrantTypes("authorization_code", "password", "refresh_token")
                .scopes("read", "write")
                .accessTokenValiditySeconds(60 * 60)
                .refreshTokenValiditySeconds(6 * 60 * 60)
                .autoApprove(true);
    }

    /**
     * Configure the non-security features of the Authorization Server endpoints, like token store, token
     * customizations, user approvals and grant types. You shouldn't need to do anything by default, unless you need
     * password grants, in which case you need to provide an {@link AuthenticationManager}.
     *
     * @param endpoints the endpoints configurer
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }

}
