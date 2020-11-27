```java
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
```