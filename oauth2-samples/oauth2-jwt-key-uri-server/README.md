# Spring Cloud Security: OAuth2 JWT Server

##Checkout
```bash
] git clone https://github.com/lllabs/l2labs-spring-cloud-samples.git
```

## Run
```bash
] cd PROJECT_ROOT
] ./gradlew :oauth2-samples:oauth2-jwt-key-uri-server:bootRun
```

## Test
### Get JWT SigningKey
```bash
] curl --location --request GET 'http://localhost:8081/oauth/token_key' \
--user client-id:client-secret

{
    "alg":"HMACSHA256",
    "value":"SigningKey"
}
```

### Get Access Token
```bash
] curl --location --request POST 'http://localhost:8081/oauth/token' \
--user client-id:client-secret \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'grant_type=password' \
--data-urlencode 'username=admin' \
--data-urlencode 'password=111'

{
    "access_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MDY5ODk4MzQsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9NQU5BR0VSIiwiUk9MRV9BRE1JTiJdLCJqdGkiOiIwMTBlNzBiYS1hZDhhLTQ1NzQtOWViNS1mMTI2MmYzYzU5OTkiLCJjbGllbnRfaWQiOiJjbGllbnQtaWQiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.N86kDwfc8pbHsAbs7hihDYmK8g_PLKt7x4kApGQgsR4",
    "token_type":"bearer",
    "refresh_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdLCJhdGkiOiIwMTBlNzBiYS1hZDhhLTQ1NzQtOWViNS1mMTI2MmYzYzU5OTkiLCJleHAiOjE2MDcwMDc4MzQsImF1dGhvcml0aWVzIjpbIlJPTEVfTUFOQUdFUiIsIlJPTEVfQURNSU4iXSwianRpIjoiN2JkMzRkMWYtZDY5YS00Mjk3LThlYTktNzk4OWM4MDhkNmQ4IiwiY2xpZW50X2lkIjoiY2xpZW50LWlkIn0.CdRM3h-KZLhTVgwcLTnD_VSwVGbfuTgtBAU1doSpt_M",
    "expires_in":3599,
    "scope":"read write",
    "jti":"010e70ba-ad8a-4574-9eb5-f1262f3c5999"
}
```

### Get User Info with Access Token
```bash
] curl --location --request POST 'http://localhost:8081/oauth/check_token' \
--user client-id:client-secret \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MDY5ODk4MzQsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9NQU5BR0VSIiwiUk9MRV9BRE1JTiJdLCJqdGkiOiIwMTBlNzBiYS1hZDhhLTQ1NzQtOWViNS1mMTI2MmYzYzU5OTkiLCJjbGllbnRfaWQiOiJjbGllbnQtaWQiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.N86kDwfc8pbHsAbs7hihDYmK8g_PLKt7x4kApGQgsR4'

{
    "timestamp":"2020-12-03T09:38:42.730+00:00",
    "status":403,
    "error":"Forbidden",
    "message":"",
    "path":"/oauth/check_token"
} 
```
* OAuth2 Server 에서는 JWT 발급만 하고, Oauth2 Resource(API) Server 에서 JWT 를 검증하고 파싱 하므로,
서버의 `/oauth/check_token` endpoint 는 `denyAll()` 로 설정 하여 `disable` 시킨다.