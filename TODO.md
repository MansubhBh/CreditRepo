
Controller Test

Sonar - Enable Code Quality check
logback - Enable Logging
Jacoco - Code Coverage
CreditCard Service with TDD
    - store
    - list
    - search
    in memory
CreditCard Controller with Test
----
MySQL server -> create table,
                new repository, update qualifier
                coverage (JdbcTemplate -> JdbcTemplate mockTemplate = Mockito.mock(JdbcTemplate.class);
Make logback message colourful janino/jansi


Kubernetes, Docker
Elasticsearch

----

Performance Test Gatling
---
Implement one against mysql
    - Create Table
    - Create Sample Inserts
    - Create Separate Service

Implement one against elasticsearch
    - Create Index
    - Update on Startup
    - Index on insert to mysql
---
Implement new service with controller
    - Repository that goes to Cassandra

---
Future
WebFlux
Micrometer

------
  learn builder pattern



Youtube/Resources:
- Mockito
- Kubernetes in 5 mins, 10min, 1hr
- Minikube
- Docker (5min, 1hr)
- Cassandra Datastax Academy
- Elasticsearch Cookbook
 

Later:
- Apache Camel
- Spark
- GoLang



------
md5,sha1, sha-256 one way ->
aes can decrypt ->

Step 1:
create table login(username, password)





 LoginRepository
    boolean checkLogin(username, password)
        use sha256 hasing on password. Hashing.sha256().hashString(password).toString()
        select count(*) from creditscore.login.... where us . and pass = ...


 interface UserTokenService

 class SimpleUserTokenService {
        createUserToken(String username) {
            return "123"+username+"456";
        }

        checkUserToken(String token) {
            token.startsWith("123") && token.startsWith("456");
        }
}

  UserToken
    access_token
    refresh_token
    validity

  LoginController {
    PostMapping("/token")
    public LoginDetail Class (username, password)
    if (!loginRepository.checkLogin()) {
        throw new RuntimeException("Invalid login");
    }
    UserToken token = userTokenService.createUserToken(username);
    return token;
  }


Add a interceptor which checks the URI and if the uri is "/token" then don't do anything
The TokenService must be injected to Interceptor too.
In the interceptor, if the url endsWith /addCreditCard then make sure you call a service that is UserTokenService which validates
    the existence of the Header -> "authorization" whose value is "Bearer <whatever the token is>"



