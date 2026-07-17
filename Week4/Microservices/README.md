# Microservices Solutions

Multi-module Maven project covering exercises from `Microservices/`.

## Modules

| Module | Port | Exercise |
|--------|------|----------|
| `eureka-server` | 8761 | Eureka discovery (composite HOL, API gateway) |
| `account-service` | 8080 | `GET /accounts/{number}` |
| `loan-service` | 8081 | `GET /loans/{number}` |
| `greet-service` | 8082 | `GET /greet` → "Hello World" |
| `api-gateway` | 9090 | Discovery routing + `LogFilter` global filter |
| `user-service` | 8083 | User management REST API |
| `order-service` | 8084 | Orders + OpenFeign call to user-service |
| `payment-service` | 8085 | Resilience4j circuit breaker + fallback |

## Run order

```bash
cd solutions/Microservices
mvn clean package -DskipTests

# Terminal 1
java -jar eureka-server/target/eureka-server-1.0.0-SNAPSHOT.jar

# Terminal 2–N (any order after Eureka is up)
java -jar account-service/target/account-service-1.0.0-SNAPSHOT.jar
java -jar loan-service/target/loan-service-1.0.0-SNAPSHOT.jar
java -jar greet-service/target/greet-service-1.0.0-SNAPSHOT.jar
java -jar api-gateway/target/api-gateway-1.0.0-SNAPSHOT.jar
java -jar user-service/target/user-service-1.0.0-SNAPSHOT.jar
java -jar order-service/target/order-service-1.0.0-SNAPSHOT.jar
java -jar payment-service/target/payment-service-1.0.0-SNAPSHOT.jar
```

## Test URLs

- Eureka dashboard: http://localhost:8761
- Account: http://localhost:8080/accounts/00987987973432
- Loan: http://localhost:8081/loans/H00987987972342
- Greet via gateway: http://localhost:9090/greet-service/greet
- Account via gateway: http://localhost:9090/accounts/00987987973432
- Payment: `curl -X POST "http://localhost:8085/payments?amount=500"`

## Additional exercise guides

See `docs/` for OAuth2/JWT and load-balancing reference solutions from the sample PDFs.
