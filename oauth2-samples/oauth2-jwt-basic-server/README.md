# Spring Cloud Security: OAuth2 JWT Server

##Checkout
```bash
] git clone https://github.com/lllabs/l2labs-spring-cloud-samples.git
```

## Run
```bash
] cd PROJECT_ROOT
] ./gradlew :oauth2-samples:oauth2-jwt-basic-server:bootRun
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

### Get User Info with Access Token
```bash
] curl --location --request POST 'http://localhost:8081/oauth/check_token' \
--user client-id:client-secret \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MDY5ODk4MzQsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9NQU5BR0VSIiwiUk9MRV9BRE1JTiJdLCJqdGkiOiIwMTBlNzBiYS1hZDhhLTQ1NzQtOWViNS1mMTI2MmYzYzU5OTkiLCJjbGllbnRfaWQiOiJjbGllbnQtaWQiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.N86kDwfc8pbHsAbs7hihDYmK8g_PLKt7x4kApGQgsR4'

{
    "user_name":"admin",
    "scope":[
        "read",
        "write"
    ],
    "active":true,
    "exp":1606990765,
    "authorities":[
        "ROLE_ADMIN"
    ],
    "jti":"77de16d4-8e88-4de5-be5a-74221e329500",
    "client_id":"client-id"
}
```