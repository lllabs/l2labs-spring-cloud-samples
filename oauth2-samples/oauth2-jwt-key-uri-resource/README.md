# Spring Cloud Security: OAuth2 JWT Resource(API)

##Checkout
```bash
] git clone https://github.com/lllabs/l2labs-spring-cloud-samples.git
```

## Run
```bash
] cd PROJECT_ROOT

# Run OAuth2 Authentication Server
] ./gradlew :oauth2-samples:oauth2-jwt-key-uri-server:bootRun

# Run OAuth2 Resource(API) Server
# Resource(API) Server 를 먼저 기동하면,
# OAuth2 Server 로 부터 JWT SigningKey 를 받아 오지 못하여 에러가 발생한다.
] ./gradlew :oauth2-samples:oauth2-jwt-key-uri-resource:bootRun
```

## Test
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

### Request to Resource Server
```bash
] curl --location --request GET 'http://localhost:9090' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MDY5ODk4MzQsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9NQU5BR0VSIiwiUk9MRV9BRE1JTiJdLCJqdGkiOiIwMTBlNzBiYS1hZDhhLTQ1NzQtOWViNS1mMTI2MmYzYzU5OTkiLCJjbGllbnRfaWQiOiJjbGllbnQtaWQiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.N86kDwfc8pbHsAbs7hihDYmK8g_PLKt7x4kApGQgsR4'

Welcome Home
```