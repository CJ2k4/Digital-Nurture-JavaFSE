# Edge Services, Load Balancing & Resilience (Sample PDF)

Implemented in the `api-gateway` module. Key patterns:

## Global filter (routing & logging)

```java
@Component
public class LogFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Request: {}", exchange.getRequest().getURI());
        return chain.filter(exchange);
    }
}
```

## Load-balanced route

```properties
spring.cloud.gateway.routes[0].id=load_balanced_route
spring.cloud.gateway.routes[0].uri=lb://account-service
spring.cloud.gateway.routes[0].predicates[0]=Path=/accounts/**
```

`lb://` uses Spring Cloud LoadBalancer to pick an instance from Eureka.

## Circuit breaker (Resilience4j)

```properties
resilience4j.circuitbreaker.instances.gatewayCircuitBreaker.slidingWindowSize=10
resilience4j.circuitbreaker.instances.gatewayCircuitBreaker.failureRateThreshold=50
```

The `payment-service` module demonstrates `@CircuitBreaker` with a `fallbackMethod` when calling a slow third-party API.

## Custom load balancer (optional)

```java
@Configuration
public class LoadBalancerConfiguration {
    @Bean
    ReactorLoadBalancer<ServiceInstance> randomLoadBalancer(
            Environment env, LoadBalancerClientFactory factory) {
        String name = env.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
        return new RandomLoadBalancer(
                factory.getLazyProvider(name, ServiceInstanceListSupplier.class), name);
    }
}
```
