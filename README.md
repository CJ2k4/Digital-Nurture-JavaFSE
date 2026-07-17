# Digital Nurture 5.0 — Java Full Stack Engineer

Hands-on solutions for the Cognizant Digital Nurture 5.0 (Java FSE) Deepskilling
programme, organised by week.

Every exercise marked mandatory in `DN - Java FSE Mandatory hands-on detail.xlsx` is
covered. Several weeks also include the additional hands-on.

| Week | Topics | Mandatory | Folder |
| --- | --- | --- | --- |
| 1 | Design Patterns, DSA, PL/SQL, TDD (JUnit 5 + Mockito), SLF4J | 12 | [`Week1/`](Week1) |
| 2 | Spring Core & Maven, Spring Data JPA with Hibernate | 5 | [`Week2/`](Week2) |
| 3 | Spring REST using Spring Boot 3, JWT authentication | 6 | [`Week3/`](Week3) |
| 4 | Microservices with Spring Boot 3 and Spring Cloud | 1 | [`Week4/`](Week4) |
| 5 | Single Page Applications with React | 10 | [`Week5/`](Week5) |
| 6 | Version control with Git | 5 | [`Week6/`](Week6) |

Each week has a README listing its exercises, how to run them, and any deviations
from the course documents.

## Layout

```
Week1/  Design-Patterns/  Data-Structures-Algorithms/  JUnit-Mockito-SL4J/  PL-SQL/
Week2/  Spring-Core-Maven/LibraryManagement  Spring-Data-JPA/orm-learn
Week3/  spring-learn/
Week4/  Microservices/          (8 modules: account, loan, eureka, gateway, ...)
Week5/  React/                  (19 Create React App projects)
Week6/  GIT/                    (5 lab folders)
```

## Stack

| | |
| --- | --- |
| Java | 17 (Week 2's `LibraryManagement` targets 1.8, as its exercise requires) |
| Spring Boot | 3.2.5 |
| Spring Cloud | 2023.0.1 |
| Build | Maven 3.9 |
| React | 18 on Create React App (`react-scripts` 5.0.1) |
| Databases | H2 in memory, MySQL, Oracle (PL/SQL scripts) |

## Quick start

```bash
# Week 1 — JUnit, Mockito, SLF4J
cd Week1/JUnit-Mockito-SL4J && mvn test

# Week 2 — Spring Core (XML config + DI)
cd Week2/Spring-Core-Maven/LibraryManagement && mvn clean package
mvn exec:java -Dexec.mainClass=com.library.LibraryManagementApplication

# Week 2 — Spring Data JPA, runs on H2 with no database install
cd Week2/Spring-Data-JPA/orm-learn && mvn spring-boot:run -Dspring-boot.run.profiles=h2

# Week 3 — Spring REST + JWT, port 8083
cd Week3/spring-learn && mvn spring-boot:run
curl -s -u user:pwd http://localhost:8083/authenticate

# Week 4 — microservices
cd Week4/Microservices && mvn clean package -DskipTests
java -jar account-service/target/account-service-1.0.0-SNAPSHOT.jar

# Week 5 — any React exercise
cd Week5/React/01-myfirstreact && npm install && npm start

# Week 6 — follow SOLUTION.md in each Week6/GIT lab folder
```

## Notes

The course documents were written against Spring Boot 2 and older tooling. Where an
instruction cannot be followed on this stack — `WebSecurityConfigurerAdapter` was
removed in Spring Security 6, jjwt 0.9 does not run on Spring Boot 3, the documents'
9-byte JWT signing key is below the HS256 minimum — the deviation is implemented and
explained in that week's README rather than left silently different.

The documents also contain a few internal contradictions (a `country` DDL that
disagrees with its own entity mapping, a route annotation that disagrees with its
sample URL). Those are called out in the relevant week's README too.
