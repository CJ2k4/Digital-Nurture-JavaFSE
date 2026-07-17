# Week 2 — Spring Core & Maven, Spring Data JPA with Spring Boot and Hibernate

Mandatory hands-on only, per `DN - Java FSE Mandatory hands-on detail.xlsx`.

| Skill | Exercise | Location |
| --- | --- | --- |
| Spring Core and Maven | Exercise 1: Configuring a Basic Spring Application | `Spring-Core-Maven/LibraryManagement` |
| Spring Core and Maven | Exercise 2: Implementing Dependency Injection | `Spring-Core-Maven/LibraryManagement` |
| Spring Core and Maven | Exercise 4: Creating and Configuring a Maven Project | `Spring-Core-Maven/LibraryManagement/pom.xml` |
| Spring Data JPA, Hibernate | Spring Data JPA - Quick Example | `Spring-Data-JPA/orm-learn` |
| Spring Data JPA, Hibernate | Difference between JPA, Hibernate and Spring Data JPA | `Spring-Data-JPA/Difference-between-JPA-Hibernate-and-SpringDataJPA.md` |

Exercises 1, 2 and 4 all build the same `LibraryManagement` project, so they live in
one Maven module rather than three copies.

---

## Spring Core and Maven — LibraryManagement

```bash
cd Spring-Core-Maven/LibraryManagement
mvn clean package
mvn exec:java -Dexec.mainClass=com.library.LibraryManagementApplication
```

### Exercise 1: Configuring a Basic Spring Application

| Requirement | Where |
| --- | --- |
| Maven project named LibraryManagement | `pom.xml` |
| Spring Core dependencies | `spring-context` in `pom.xml` |
| `applicationContext.xml` in `src/main/resources` | present |
| Beans for `BookService` and `BookRepository` | defined in that XML |
| `com.library.service.BookService` | present |
| `com.library.repository.BookRepository` | present |
| Main class that loads the context | `LibraryManagementApplication` |

The main class prints every bean the container registered, which is how you can see
the XML was actually read:

```
bean: bookRepository -> com.library.repository.BookRepository
bean: bookService -> com.library.service.BookService
```

### Exercise 2: Implementing Dependency Injection

`BookService` never constructs a `BookRepository`. It exposes a setter, and the
container supplies the dependency:

```xml
<bean id="bookRepository" class="com.library.repository.BookRepository"/>

<bean id="bookService" class="com.library.service.BookService">
    <property name="bookRepository" ref="bookRepository"/>
</bean>
```

`<property name="bookRepository">` is what makes Spring call
`setBookRepository(...)` — the property name maps to the setter, not to the field.

The main class proves the wiring rather than assuming it:

```
BookRepository injected into BookService: true
Injected instance is the container's bean: true
```

The second line matters. It confirms `BookService` received *the container's*
singleton, not some other instance — which is the whole point of IoC: the object does
not create or look up its own dependency, it is handed one.

### Exercise 4: Creating and Configuring a Maven Project

| Requirement | Where |
| --- | --- |
| Spring Context, Spring AOP, Spring WebMVC dependencies | `<dependencies>` in `pom.xml` |
| Maven Compiler Plugin configured for Java 1.8 | `maven-compiler-plugin` with `<source>1.8</source>` / `<target>1.8</target>` |

Spring 5.3.x is used here rather than Spring 6, because Spring 6 requires Java 17+ and
the exercise explicitly asks for a Java 1.8 target. The two constraints are not
compatible; the exercise wins.

---

## Spring Data JPA — orm-learn

Group `com.cognizant`, artifact `orm-learn`, per the hands-on document.

### Run against H2 (no database install needed)

```bash
cd Spring-Data-JPA/orm-learn
mvn spring-boot:run -Dspring-boot.run.profiles=h2
mvn test
```

`schema.sql` creates the `country` table and `data.sql` populates it, so the H2
profile runs the exercise start to finish with no setup.

### Run against MySQL (as the document specifies)

```bash
mysql -u root -p
mysql> create schema ormlearn;
mysql> use ormlearn;
mysql> source src/main/resources/schema.sql;
mysql> source src/main/resources/data.sql;
```

Then `mvn spring-boot:run`. The default `application.properties` holds the MySQL
datasource, log levels and log pattern given in the document.

### Structure

| Class | Role |
| --- | --- |
| `model/Country` | `@Entity`, `@Table(name="country")`, `@Id`, `@Column` |
| `repository/CountryRepository` | `@Repository`, extends `JpaRepository<Country, String>` |
| `service/CountryService` | `@Service`, autowires the repository, `@Transactional getAllCountries()` |
| `OrmLearnApplication` | `@SpringBootApplication`, gets `CountryService` from the context, calls `testGetAllCountries()` |

Running it produces exactly the log flow the document asks for:

```
INFO  OrmLearnApplication  main                 Inside main
INFO  OrmLearnApplication  testGetAllCountries  Start
DEBUG OrmLearnApplication  testGetAllCountries  countries=[Country [code=IN, name=India], ...]
INFO  OrmLearnApplication  testGetAllCountries  End
```

### Two deviations from the document, both deliberate

**1. Column names.** The document's DDL creates the table with `co_code` and
`co_name`:

```sql
create table country(co_code varchar(2) primary key, co_name varchar(50));
```

but its `Country` snippet maps `@Column(name="code")` and `@Column(name="name")`.
Those disagree, and the mapping as printed would fail against that table with
`ddl-auto=validate`. Every `INSERT` later in the same document uses
`co_code`/`co_name`, so the DDL is the correct side and the entity maps to those.

**2. `jakarta` instead of `javax`.** The document predates Spring Boot 3, where the
JPA annotations moved from `javax.persistence` to `jakarta.persistence`. Same
specification, renamed package. This project follows the rest of the repository,
which is on Spring Boot 3.2.5 / Java 17.

### Tests

`CountryServiceTest` runs against the H2 profile and covers the three things the
exercise is really asserting: the context loads and injects the service, `findAll()`
returns the rows from the `country` table, and `Country` is mapped to the right
columns.

```
Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
```
