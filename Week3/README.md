# Week 3 — Spring REST using Spring Boot 3

Mandatory hands-on only, per `DN - Java FSE Mandatory hands-on detail.xlsx`.

| Document | Exercise | Where |
| --- | --- | --- |
| `1. spring-rest-handson` | Create a Spring Web Project using Maven | `spring-learn/pom.xml`, `SpringLearnApplication` |
| `1. spring-rest-handson` | Spring Core – Load Country from Spring Configuration XML | `Country`, `country.xml`, `SpringLearnApplication.displayCountry()` |
| `2. spring-rest-handson` | Hello World RESTful Web Service | `controller/HelloController` |
| `2. spring-rest-handson` | REST - Country Web Service | `controller/CountryController.getCountryIndia()` |
| `2. spring-rest-handson` | REST - Get country based on country code | `controller/CountryController.getCountry()`, `service/CountryService` |
| `5. JWT-handson` | Create authentication service that returns JWT | `controller/AuthenticationController`, `security/SecurityConfig` |

All six build one `spring-learn` project (group `com.cognizant`), as the documents intend.

## Run

```bash
cd spring-learn
mvn spring-boot:run     # starts on port 8083
mvn test                # 11 tests
```

## Endpoints

| Method | URL | Auth | Response |
| --- | --- | --- | --- |
| GET | `/hello` | none | `Hello World!!` |
| GET | `/country` | none | `{"code":"IN","name":"India"}` |
| GET | `/countries/{code}` | basic, role USER | the matching country, 404 if absent |
| GET | `/authenticate` | basic, role USER or ADMIN | `{"token":"<jwt>"}` |

In-memory users, both with password `pwd`: **user** (role USER), **admin** (role ADMIN).

## Verified against the documents' own curl commands

```bash
$ curl -s http://localhost:8083/hello
Hello World!!

$ curl -s http://localhost:8083/country
{"code":"IN","name":"India"}

$ curl -s -u user:pwd http://localhost:8083/countries/in
{"code":"IN","name":"India"}

$ curl -s -u user:pwd http://localhost:8083/authenticate
{"token":"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNzg0MjY0ODY1LCJleHAiOjE3ODQyNjYwNjV9..."}
```

Decoding that token gives exactly the structure the JWT document describes:

```
header  {"alg":"HS256"}
payload {"sub":"user","iat":1784264865,"exp":1784266065}
```

`exp - iat` is 1200 seconds — the 20-minute expiry the document asks for.

Security responses also match the document:

| Command | Result |
| --- | --- |
| `curl -u user:pwd /authenticate` | 200 with token |
| `curl /authenticate` (no credentials) | 401 Unauthorized |
| `curl -u user:wrong /authenticate` | 401 Unauthorized |
| `curl -u admin:pwd /countries/IN` | 403 Forbidden — right credentials, wrong role |

## Spring Core – Load Country from Spring Configuration XML

`country.xml` defines the country bean by property injection, and `Country` logs inside
its constructor and every getter/setter, so the log shows what the container actually
calls:

```
Inside Country Constructor.
Inside setCode(), code=IN
Inside setName(), name=India
Country : Country [code=IN, name=India]
```

That ordering is the lesson: Spring constructs the bean with the no-arg constructor
first, then injects each `<property>` through its setter — it does not pass values to a
constructor.

## Deviations from the documents

These documents were written for Spring Boot 2. This project is Spring Boot 3.2.5 /
Java 17, matching the rest of the repository. Each change below is forced, not
cosmetic.

**1. `WebSecurityConfigurerAdapter` no longer exists.** The JWT document extends it
and overrides two `configure()` methods. Spring Security 6 removed the class
outright. The modern equivalent is a `SecurityFilterChain` bean plus an
`InMemoryUserDetailsManager`, which is what `SecurityConfig` does — same two users,
same roles, same rules.

**2. jjwt 0.9.0 → 0.12.5.** Version 0.9.0 depends on `javax.xml.bind` and the JJWT
API of that era (`Jwts.builder().setSubject(...)`, `signWith(SignatureAlgorithm, String)`).
It does not work on Spring Boot 3. The 0.12.x API is `subject(...)`, `signWith(SecretKey)`.

**3. The document's signing key is too short to be legal.** It uses `"secretkey"` —
9 bytes. HS256 requires a key of at least 256 bits (32 bytes) per RFC 7518, and jjwt
0.12 enforces this rather than silently accepting it. `jwt.secret` in
`application.properties` is a 40-byte key. The token is otherwise identical in
structure.

**4. Package name.** The documents say `com.cognizant.spring-learn`. A hyphen is not
legal in a Java package name, and the documents' own import statements use
`com.cognizant.springlearn`. This project uses the latter.

**5. `/countries/{code}` vs `/country/in`.** The document specifies
`@GetMapping("/countries/{code}")` but then shows `http://localhost:8083/country/in`
as the sample request. Those disagree. The explicit annotation wins, so the route is
`/countries/{code}`.

**6. Port.** Document 2 uses 8083 in its samples, the JWT document uses 8090. This
project uses 8083, set in `application.properties`.

**7. `/hello` and `/country` are `permitAll`.** Enabling Spring Security for the JWT
hands-on would otherwise lock the two earlier hands-on behind basic auth, and their
documented curl commands (`curl -s http://localhost:8083/hello` with no credentials)
would stop working. Leaving those two open keeps every documented command in this
week's scope reproducible. `/countries/**` and `/authenticate` are secured as the
JWT document specifies.

## Scope note

The JWT document continues past the mandatory item into "Authorize based on JWT" —
a `JwtAuthorizationFilter` that validates the token on subsequent requests. Only
"Create authentication service that returns JWT" is on the mandatory sheet, so the
filter is not implemented here. The tests verify the token is well-formed and signed,
by parsing it back and asserting the subject is the authenticated user.

## Tests

`SpringLearnApplicationTests` — 11 tests, one or more per mandatory exercise:

```
Tests run: 11, Failures: 0, Errors: 0, Skipped: 0
```

| Test | Covers |
| --- | --- |
| `contextLoads` | `CountryController` is loaded |
| `testSayHello` | Hello World service |
| `testGetCountryIndia` | `/country` returns code IN, name India |
| `testGetCountryByCode` | `/countries/{code}` |
| `testGetCountryByCodeIsCaseInsensitive` | `in` and `jP` resolve |
| `testGetCountryNotFoundReturns404` | absent code |
| `testCountryServiceThrowsWhenCodeMissing` | service throws `CountryNotFoundException` |
| `testAuthenticateReturnsToken` | `/authenticate` returns a token |
| `testTokenSubjectIsTheAuthenticatedUser` | token parses and its subject is `user` |
| `testAuthenticateRequiresCredentials` | 401 without credentials |
| `testCountriesRequiresUserRole` | 403 for admin |
