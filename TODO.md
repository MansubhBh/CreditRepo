
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

----
Video Prometheus Monitoring & Grafana


----
Create these endpoints in creditscore app
/liveness
/readiness

configure creditscore.yaml deployment file to use readiness and liveness probe.
liveness is used to check if your pod is up and responsive
readiness is used to check if your pod is ready to service requests. pod that is not ready will not receive any traffic.






