# Spring Cloud Security: OAuth2 Basic Resource(API)

## Checkout
```bash
] git clone https://github.com/lllabs/l2labs-spring-cloud-samples.git
```

## Run
```bash
] cd PROJECT_ROOT

# Run OAuth2 Authentication Server
] ./gradlew :oauth2-samples:oauth2-basic-server:bootRun

# Run OAuth2 Resource(API) Server
] ./gradlew :oauth2-samples:oauth2-basic-resource:bootRun
```

## Test
### Issue Access Token
```bash
] curl --location --request POST 'http://localhost:8081/oauth/token' \
--user client-id:client-secret \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'grant_type=password' \
--data-urlencode 'username=admin' \
--data-urlencode 'password=111'

{
    "access_token":"8bce7d8a-cc4f-4e6b-9c79-8558ee2913f5",
    "token_type":"bearer",
    "refresh_token":"41e01c98-b451-4969-a25a-0011d215b9de",
    "expires_in":1727,
    "scope":"read write"
}
```

### Request to Resource Server with Access Token
```bash
] curl --location --request GET 'http://localhost:9090' \
--header 'Authorization: Bearer 8bce7d8a-cc4f-4e6b-9c79-8558ee2913f5'

Welcome Home
```