# OAuth 2 / JWT / Resource Server (Sample exercises PDF)

Spring Boot 3 security configuration using the modern filter-chain API (not `WebSecurityConfigurerAdapter`).

## Exercise 1: OAuth2 Client

```java
@Configuration
@EnableWebSecurity
public class OAuth2ClientSecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .oauth2Login(Customizer.withDefaults())
                .build();
    }
}

@RestController
class UserController {
    @GetMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }
}
```

`application.yml`:

```yaml
spring:
  security:
    oauth2:
      client:
        registration:
          my-client:
            client-id: YOUR_CLIENT_ID
            client-secret: YOUR_CLIENT_SECRET
            scope: openid, profile, email
            authorization-grant-type: authorization_code
            redirect-uri: "{baseUrl}/login/oauth2/code/{registrationId}"
        provider:
          my-provider:
            authorization-uri: https://accounts.google.com/o/oauth2/auth
            token-uri: https://oauth2.googleapis.com/token
            user-info-uri: https://openidconnect.googleapis.com/v1/userinfo
            user-name-attribute: sub
```

Dependencies: `spring-boot-starter-security`, `spring-boot-starter-oauth2-client`

## Exercise 2: Resource Server

```java
@Configuration
@EnableWebSecurity
public class ResourceServerConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .build();
    }
}
```

`application.yml`:

```yaml
spring:
  security:
    oauth2:
      resourceserver:
        jwt:
          issuer-uri: https://issuer.example.com
```

## Exercise 3: Custom JWT

Use `io.jsonwebtoken:jjwt-api` (0.12.x) with a `OncePerRequestFilter` that:

1. Reads `Authorization: Bearer <token>`
2. Validates signature with a shared secret
3. Sets `SecurityContextHolder` authentication

See the exercise PDF for `JwtTokenProvider`, `JwtTokenFilter`, and `SecurityConfig` structure.
