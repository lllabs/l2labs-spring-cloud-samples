package spring.cloud.samples.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;

@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
public class OAuth2JwtServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // Resource(API) Server 에서 OAuth2 서버에 설정된 JWT 검증 SigningKey 를 조회 할 수 있도록
        // /oauth/token_key endpoint 의 접근권한을 설정한다. 기본 접근권한은 "denyAll()"
        // isAuthenticated() 로 설정 할 경우 Resource(API) Server 에 client-id, client-secret 이 OAuth2 서버와 동일하게 설정 되어야 한다.
        security.tokenKeyAccess("isAuthenticated()");   // or "permitAll()"
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // OAuth2 인증을 사용할 Client 설정.
        clients.inMemory()
                .withClient("client-id")
                .secret("{noop}client-secret")
                .authorizedGrantTypes("authorization_code", "password", "refresh_token")
                .scopes("read", "write")
                .accessTokenValiditySeconds(60 * 60)
                .refreshTokenValiditySeconds(6 * 60 * 60)
                .autoApprove(true);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                // 사용자(User) 인증을 위한 AuthenticationManager 설정
                .authenticationManager(authenticationManager)

                // JWT 변환을 위한 TokenConverter 설정
                .accessTokenConverter(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        KeyStoreKeyFactory keyStore = new KeyStoreKeyFactory(new ClassPathResource("keystore.jks"), "keypass".toCharArray());
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyStore.getKeyPair("jwt"));
        return converter;
    }

}
