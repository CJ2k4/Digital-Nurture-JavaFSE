# Week 1 — Design Principles & Patterns, DSA, PL/SQL, TDD, Logging

Mandatory hands-on per `DN - Java FSE Mandatory hands-on detail.xlsx` — 12 exercises
across five skills.

| Skill | Exercise | Location |
| --- | --- | --- |
| Design principles & Patterns | Exercise 1: Implementing the Singleton Pattern | `Design-Patterns/SingletonPatternExample` |
| Design principles & Patterns | Exercise 2: Implementing the Factory Method Pattern | `Design-Patterns/FactoryMethodPatternExample` |
| Data structures and Algorithms | Exercise 2: E-commerce Platform Search Function | `Data-Structures-Algorithms/EcommercePlatformSearch` |
| Data structures and Algorithms | Exercise 7: Financial Forecasting | `Data-Structures-Algorithms/FinancialForecasting` |
| PL/SQL programming | Exercise 1: Control Structures | `PL-SQL/01_control_structures.sql` |
| PL/SQL programming | Exercise 3: Stored Procedures | `PL-SQL/03_stored_procedures.sql` |
| TDD using JUnit5 and Mockito | Exercise 1: Setting Up JUnit | `JUnit-Mockito-SL4J` — `junit/basic/JUnitSetupTest` |
| TDD using JUnit5 and Mockito | Exercise 3: Assertions in JUnit | `JUnit-Mockito-SL4J` — `junit/basic/AssertionsTest` |
| TDD using JUnit5 and Mockito | Exercise 4: AAA Pattern, Test Fixtures, Setup and Teardown | `JUnit-Mockito-SL4J` — `junit/basic/CalculatorAaaTest` |
| TDD using JUnit5 and Mockito | Mockito Exercise 1: Mocking and Stubbing | `JUnit-Mockito-SL4J` — `mockito/basic/MyServiceTest` |
| TDD using JUnit5 and Mockito | Mockito Exercise 2: Verifying Interactions | `JUnit-Mockito-SL4J` — `mockito/basic/MyServiceTest` |
| SLF4J logging framework | Exercise 1: Logging Error Messages and Warning Levels | `JUnit-Mockito-SL4J` — `logging/LoggingExampleTest` |

The `JUnit-Mockito-SL4J` and `PL-SQL` projects also cover the non-mandatory exercises
in their respective documents.

## Contents

| Folder | What it is |
| --- | --- |
| `Design-Patterns/` | Two plain Java projects, one per pattern. See its README. |
| `Data-Structures-Algorithms/` | Two plain Java projects, with the Big O and recursion analysis the exercises require. See its README. |
| `JUnit-Mockito-SL4J/` | Maven project — JUnit 5, Mockito, Spring test and SLF4J exercises. |
| `PL-SQL/` | Oracle PL/SQL scripts for all 7 exercise sets. See its README. |

## Run

```bash
# Design patterns and DSA — plain Java, no build tool
cd Design-Patterns
javac -d out SingletonPatternExample/src/com/digitalnurture/week1/singleton/*.java
java -cp out com.digitalnurture.week1.singleton.SingletonPatternTest

# JUnit / Mockito / SLF4J
cd JUnit-Mockito-SL4J && mvn test        # 63 tests

# PL/SQL — run schema.sql first, then the exercise scripts in SQL*Plus or SQL Developer
```

## Two fixes applied to JUnit-Mockito-SL4J

`mvn test` did not run in this project. Both problems predate the week-based
restructure — they are present in the original commit `a2e77d0` — and both are now
fixed. All 63 tests pass.

**1. Test compilation failed.** `junit/advanced/AllTests` is a `@Suite` that
`@SelectClasses({CalculatorTest.class, AssertionsTest.class, ...})`, but those two
classes live in `junit.basic` and were declared package-private:

```
AllTests.java:[3,38] com.digitalnurture.junit.basic.AssertionsTest is not public in
com.digitalnurture.junit.basic; cannot be accessed from outside package
```

JUnit 5 allows package-private test classes, but a suite in a *different* package
cannot reference them. Both are now `public`, which is what the cross-package suite
requires.

**2. Three Spring tests errored on H2.** `User` is `@Entity` with no `@Table`, so
Hibernate mapped it to a table called `user`. `USER` is a reserved keyword in H2 2.x,
so every insert was a syntax error:

```
Syntax error in SQL statement "insert into [*]user (name,id) values (?,default)";
expected "identifier" [42001-224]
```

The entity now declares `@Table(name = "users")`.
