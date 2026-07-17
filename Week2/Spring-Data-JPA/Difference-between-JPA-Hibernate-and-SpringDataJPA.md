# Difference between JPA, Hibernate and Spring Data JPA

Mandatory hands-on from `1. spring-data-jpa-handson.docx`.

## Summary

| | What it is | Ships an implementation? | Layer |
| --- | --- | --- | --- |
| **JPA** | JSR 338 specification for persisting, reading and managing data from Java objects | No — specification only | Standard / API |
| **Hibernate** | An ORM tool that implements the JPA specification | Yes | JPA provider |
| **Spring Data JPA** | An abstraction over a JPA provider that removes boilerplate | No — delegates to a provider | Above JPA |

They are not competing choices. They stack:

```
Application code
      │
Spring Data JPA      <- removes boilerplate, manages transactions
      │
JPA (javax/jakarta.persistence)   <- the specification: @Entity, EntityManager
      │
Hibernate            <- the implementation that does the actual work
      │
JDBC / database
```

## Java Persistence API (JPA)

- JSR 338 specification for persisting, reading and managing data from Java objects.
- Defines the contract — `@Entity`, `@Table`, `@Id`, `@Column`, `EntityManager` — and
  nothing more.
- **Does not contain a concrete implementation of the specification.** JPA alone
  cannot talk to a database.
- Hibernate is one of the implementations of JPA. EclipseLink and OpenJPA are others.

Because it is a standard, code written against JPA can switch providers without
rewriting the entity classes.

## Hibernate

- An ORM tool that implements JPA.
- Does the real work: generating SQL, mapping rows to objects, managing the session,
  caching, lazy loading.
- Predates JPA and has its own native API (`SessionFactory`, `Session`), which is why
  you can use Hibernate directly *or* through the JPA API.

Core objects of the Hibernate framework:

| Object | Role |
| --- | --- |
| `SessionFactory` | Heavyweight, thread-safe factory for sessions; created once per app |
| `Session` | Short-lived, single-threaded unit of work with the database |
| `Transaction` | Unit of work boundary — `beginTransaction()`, `commit()`, `rollback()` |
| `TransactionFactory` | Creates transaction instances |
| `ConnectionProvider` | Pool/source of JDBC connections |

## Spring Data JPA

- **Does not have a JPA implementation.** It is another level of abstraction over a
  JPA implementation provider like Hibernate.
- Reduces boilerplate code — you declare an interface and Spring generates the
  implementation at runtime.
- Manages transactions, via `@Transactional`.
- Derives queries from method names (`findByNameContaining`), so common queries need
  no SQL or JPQL at all.

## Code comparison

The difference is clearest in a create operation.

### Hibernate (native API)

Every call manages the session and transaction by hand:

```java
/* Method to CREATE an employee in the database */
public Integer addEmployee(Employee employee) {
    Session session = factory.openSession();
    Transaction tx = null;
    Integer employeeID = null;
    try {
        tx = session.beginTransaction();
        employeeID = (Integer) session.save(employee);
        tx.commit();
    } catch (HibernateException e) {
        if (tx != null) tx.rollback();
        e.printStackTrace();
    } finally {
        session.close();
    }
    return employeeID;
}
```

Open the session, begin, commit, catch, roll back, close — repeated in every method,
and a missed `close()` leaks a connection.

### Spring Data JPA

`EmployeeRepository.java`

```java
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
```

`EmployeeService.java`

```java
@Autowired
private EmployeeRepository employeeRepository;

@Transactional
public void addEmployee(Employee employee) {
    employeeRepository.save(employee);
}
```

The repository has no body — Spring Data generates it. `@Transactional` replaces the
try/catch/commit/rollback/close block. Hibernate still does the work underneath; it
has just stopped being the application's problem.

## How this maps to the orm-learn project

The Quick Example in `orm-learn/` is this stack end to end:

| Layer | In this project |
| --- | --- |
| JPA | `Country` uses `jakarta.persistence` annotations — `@Entity`, `@Table`, `@Id`, `@Column` |
| Hibernate | The provider Spring Boot auto-configures; it emits the SQL visible in the trace logs |
| Spring Data JPA | `CountryRepository extends JpaRepository<Country, String>` — an empty interface that still has `findAll()` |

`CountryService.getAllCountries()` calls `countryRepository.findAll()` and is annotated
`@Transactional`. No `Session`, no `EntityManager`, no transaction handling — which is
exactly the boilerplate reduction described above.

> **Note on Jakarta:** the hands-on document imports `javax.persistence`. From Spring
> Boot 3 onwards the same JPA annotations moved to `jakarta.persistence` — same
> specification, new package name after the Java EE to Jakarta EE transfer. This
> project uses `jakarta` to match the rest of the repository, which is on Spring Boot 3.

## Reference links

- <https://dzone.com/articles/what-is-the-difference-between-hibernate-and-sprin-1>
- <https://www.javaworld.com/article/3379043/what-is-jpa-introduction-to-the-java-persistence-api.html>
