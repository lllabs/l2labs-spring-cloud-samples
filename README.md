# l2labs-spring-cloud-samples

## JDBC
* DBMS : MySQL
* HOST : localhost
* DATABASE : spring_cloud
* USERNAME : test
* PASSWORD : test 

```SQL
-- Create User
CREATE user 'test'@'%' identified by 'test';
CREATE user 'test'@'localhost' identified by 'test';

-- Create Database
CREATE DATABASE `spring_cloud`;
GRANT ALL ON spring_cloud.* to test@'%';
GRANT ALL ON spring_cloud.* to test@'localhost';
FLUSH PRIVILEGES;
```
