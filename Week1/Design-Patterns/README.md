# Design Patterns and Principles

Mandatory hands-on for `Engineering concepts/Design Patterns and Principles.docx`.

| Exercise | Pattern | Project |
| --- | --- | --- |
| 1 | Singleton | `SingletonPatternExample` |
| 2 | Factory Method | `FactoryMethodPatternExample` |

## Run

```bash
# Exercise 1
javac -d out SingletonPatternExample/src/com/digitalnurture/week1/singleton/*.java
java -cp out com.digitalnurture.week1.singleton.SingletonPatternTest

# Exercise 2
javac -d out FactoryMethodPatternExample/src/com/digitalnurture/week1/factory/*.java
java -cp out com.digitalnurture.week1.factory.FactoryMethodPatternTest
```

## Exercise 1: Singleton Pattern

A logging utility must have exactly one instance for the application lifetime.

Three things enforce that in `Logger`:

- the instance is held in a `private static` field
- the constructor is `private`, so no caller can use `new Logger()`
- `getInstance()` is the only way in, and creates the instance only if it does not exist yet

`SingletonPatternTest` requests the logger three times and compares the references
with `==`. All three are the same object, and the `"Logger instance created"`
message from the constructor prints only once — proof the constructor ran a single
time regardless of how many times `getInstance()` was called.

### Note on thread safety

The lazy `if (instance == null)` check above is the form the exercise describes, and
it is fine for single-threaded use. Under concurrency two threads can both pass the
null check and create separate instances. Real-world options:

```java
// eager — simplest, thread-safe, instance created at class load
private static final Logger INSTANCE = new Logger();
public static Logger getInstance() { return INSTANCE; }

// holder idiom — thread-safe and still lazy
private static class Holder { static final Logger INSTANCE = new Logger(); }
public static Logger getInstance() { return Holder.INSTANCE; }
```

## Exercise 2: Factory Method Pattern

A document management system creates Word, PDF and Excel documents without the
calling code depending on the concrete classes.

| Role | Type |
| --- | --- |
| Product interface | `Document` |
| Concrete products | `WordDocument`, `PdfDocument`, `ExcelDocument` |
| Creator | `DocumentFactory` (abstract, declares `createDocument()`) |
| Concrete creators | `WordDocumentFactory`, `PdfDocumentFactory`, `ExcelDocumentFactory` |

Each subclass decides which concrete `Document` to instantiate. `DocumentFactory`
never names a concrete type — it only declares that one will be produced.

`newDocument()` shows the reason the pattern exists: the base class defines the
shared steps (create, then open) while the subclass supplies only the creation
step. `FactoryMethodPatternTest` loops over an array of factories typed as
`DocumentFactory` and calls the same method on each; adding a new document type
means adding two classes and changing no existing code.
