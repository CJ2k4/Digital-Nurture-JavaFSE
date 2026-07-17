server.port=9090
spring.application.name=api-gateway
eureka.client.service-url.defaultZone=http://localhost:8761/eureka

spring.cloud.gateway.discovery.locator.enabled=true
spring.cloud.gateway.discovery.locator.lower-case-service-id=true

spring.cloud.gateway.routes[0].id=account-route
spring.cloud.gateway.routes[0].uri=lb://account-service
spring.cloud.gateway.routes[0].predicates[0]=Path=/accounts/**

spring.cloud.gateway.routes[1].id=loan-route
spring.cloud.gateway.routes[1].uri=lb://loan-service
spring.cloud.gateway.routes[1].predicates[0]=Path=/loans/**

resilience4j.circuitbreaker.instances.gatewayCircuitBreaker.registerHealthIndicator=true
resilience4j.circuitbreaker.instances.gatewayCircuitBreaker.slidingWindowSize=10
resilience4j.circuitbreaker.instances.gatewayCircuitBreaker.failureRateThreshold=50
