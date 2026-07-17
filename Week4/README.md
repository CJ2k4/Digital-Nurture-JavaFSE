# Week 4 — Microservices with Spring Boot 3 and Spring Cloud

Mandatory hands-on per `DN - Java FSE Mandatory hands-on detail.xlsx`.

| Document | Exercise | Status |
| --- | --- | --- |
| `2. Microservices with API gateway` | Creating Microservices for account and loan | done — `Microservices/account-service`, `Microservices/loan-service` |

That single row is the whole mandatory scope for Week 4. "Create Eureka Discovery
Server and register microservices" sits in the *Additional important hands-on*
column, not the mandatory one.

The `Microservices/` project already went beyond the mandatory item — it also has
`eureka-server`, `api-gateway` with a global `LogFilter`, `greet-service`, and
Feign/Resilience4j work. See `Microservices/README.md` for the full module list.

## The mandatory exercise

Two independent Spring Boot REST services, each its own Maven module with its own
`pom.xml`, no backend connectivity, running on different ports.

| Service | Endpoint | Port |
| --- | --- | --- |
| account | `GET /accounts/{number}` | 8080 |
| loan | `GET /loans/{number}` | 8081 |

### Verified against the document's sample values

```bash
$ curl -s http://localhost:8080/accounts/00987987973432
{"number":"00987987973432","balance":234343,"type":"savings"}

$ curl -s http://localhost:8081/loans/H00987987972342
{"type":"car","number":"H00987987972342","tenure":18,"emi":3258,"loan":400000}
```

Both match the document exactly:

```
account   { number: "00987987973432", type: "savings", balance: 234343 }
loan      { number: "H00987987972342", type: "car", loan: 400000, emi: 3258, tenure: 18 }
```

The services are genuinely independent — each only serves its own domain:

```bash
$ curl -o /dev/null -w "%{http_code}" http://localhost:8080/loans/H00987987972342
404
$ curl -o /dev/null -w "%{http_code}" http://localhost:8081/accounts/00987987973432
404
```

That is the point the document is making with the port conflict: two separate
applications, separately deployable, each doing one thing. Loan cannot run on 8080
because account already holds it, which is why `server.port=8081` is set in
`loan-service/src/main/resources/application.properties`.

## Run

```bash
cd Microservices
mvn clean package -DskipTests

# each service in its own terminal
java -jar account-service/target/account-service-1.0.0-SNAPSHOT.jar
java -jar loan-service/target/loan-service-1.0.0-SNAPSHOT.jar
```

Both modules include the Eureka client, so they try to reach a discovery server on
startup. To run the two services on their own without `eureka-server`, disable it:

```bash
java -jar account-service/target/account-service-1.0.0-SNAPSHOT.jar \
  --eureka.client.enabled=false \
  --eureka.client.register-with-eureka=false \
  --eureka.client.fetch-registry=false
```

Or start `eureka-server` (port 8761) first and run them unmodified.

## Fix applied to the parent pom

The modules built, but produced plain library jars rather than executable ones —
`java -jar account-service/target/account-service-1.0.0-SNAPSHOT.jar` failed with:

```
no main manifest attribute, in account-service/target/account-service-1.0.0-SNAPSHOT.jar
```

Cause: `Microservices/pom.xml` inherits from `spring-boot-starter-parent`, which
*manages* the version of `spring-boot-maven-plugin` but does not apply it. Without
the plugin declared in a `<build>` section, its `repackage` goal never runs, so
nothing rewrites the jar with a `Main-Class` and the nested dependencies.

Adding it to the parent, where all eight modules inherit it:

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

The manifests now carry `Main-Class: org.springframework.boot.loader.launch.JarLauncher`
and every module runs with `java -jar`.

This never showed up while running from an IDE — launching the application class
directly does not need a repackaged jar, which is exactly how the document has you
run it. It only surfaces from the command line.
