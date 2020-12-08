package spring.cloud.samples.oauth2.jwt.keypair.server.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerTokenServicesConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;

@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
@EnableConfigurationProperties(AuthorizationServerProperties.class)
@Import(AuthorizationServerTokenServicesConfiguration.class)
public class OAuth2JwtServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;

    private final AccessTokenConverter tokenConverter;

    //private final AuthorizationServerProperties properties;

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
                .accessTokenConverter(tokenConverter);
    }

//    @Bean
//    public JwtAccessTokenConverter accessTokenConverter() {
//        Assert.notNull(properties.getJwt().getKeyStore(), "keyStore cannot be null");
//        Assert.notNull(properties.getJwt().getKeyStorePassword(), "keyStorePassword cannot be null");
//        Assert.notNull(properties.getJwt().getKeyAlias(), "keyAlias cannot be null");
//
//        KeyPair keyPair = new KeyStoreKeyFactory(
//                new ClassPathResource("keystore.jks"), "storepass".toCharArray()
//        ).getKeyPair("security", "keypass".toCharArray());
//
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
////        converter.setKeyPair(keyPair);
//
//        return converter;
//    }

}
