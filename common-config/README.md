# Spring Cloud Samples : Common Config
## KeyPair
* https://www.lesstif.com/java/java-keytool-keystore-20775436.html

### Generate KeyStore
```bash
keytool -genkeypair \
-alias security \
-keyalg RSA \
-keystore keystore.jks \
-keypass keypass \
-storepass storepass \
-dname "CN=OAuth,OU=Unit,O=Organization,L=City,S=State,C=KR"
```