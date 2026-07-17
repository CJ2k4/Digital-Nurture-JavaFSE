# Algorithms and Data Structures

Mandatory hands-on for `Engineering concepts/Algorithms_Data Structures.docx`.

| Exercise | Topic | Project |
| --- | --- | --- |
| 2 | E-commerce Platform Search Function | `EcommercePlatformSearch` |
| 7 | Financial Forecasting | `FinancialForecasting` |

## Run

```bash
# Exercise 2
javac -d out EcommercePlatformSearch/src/com/digitalnurture/week1/search/*.java
java -cp out com.digitalnurture.week1.search.EcommerceSearchTest

# Exercise 7
javac -d out FinancialForecasting/src/com/digitalnurture/week1/forecasting/*.java
java -cp out com.digitalnurture.week1.forecasting.FinancialForecastingTest
```

## Exercise 2: E-commerce Platform Search Function

### Big O notation

Big O describes how an algorithm's work grows as the input grows, ignoring
hardware and constant factors. It answers "if the catalogue gets 10x bigger, what
happens to the search?" rather than "how many milliseconds does this take?" — which
is what makes it portable across machines and useful before anything is measured.

### Best, average and worst case

| | Linear search | Binary search |
| --- | --- | --- |
| Best | O(1) — first element | O(1) — middle element |
| Average | O(n/2) → O(n) | O(log n) |
| Worst | O(n) — last, or absent | O(log n) |

Worst case matters most: it is the guarantee. Best case is usually luck.

### Measured result

`SearchEngine` counts comparisons, so the difference is visible rather than
asserted. Searching the last element with n = 8:

```
Linear search comparisons: 8   -> O(n)
Binary search comparisons: 4   -> O(log n)
```

Linear touches every element. Binary halves the range each step: 8 → 4 → 2 → 1.

The gap widens as the catalogue grows, which is the entire point:

| Products | Linear (worst) | Binary (worst) |
| --- | --- | --- |
| 8 | 8 | 4 |
| 1,000 | 1,000 | ~10 |
| 1,000,000 | 1,000,000 | ~20 |

### Which is suitable for the platform, and why

Binary search — but the honest answer has a condition attached.

Binary search requires the data to be **sorted**, which is why
`EcommerceSearchTest` sorts a copy before searching. Sorting costs O(n log n),
so binary search only pays off when the array is sorted once and searched many
times. That is exactly an e-commerce catalogue: it changes rarely and is searched
constantly, so the sort cost is amortised across millions of queries.

Linear search stays the better choice when the data is unsorted and searched once
(sorting first would cost more than the scan), or when n is small enough that the
difference is noise.

A real platform would go further and use a `HashMap` for O(1) exact-match lookup by
id, or an inverted index for partial and multi-field text search. Binary search on a
sorted array is the right structure for ordered data and range queries
("price between X and Y"), not for free-text search.

## Exercise 7: Financial Forecasting

### Recursion

A recursive method solves a problem by calling itself on a smaller version of the
same problem, until it reaches a base case that can be answered directly.

Forecasting fits naturally because the domain is self-similar — next year's value
*is* this year's value grown by one period:

```
value(0)     = presentValue                    // base case
value(years) = value(years - 1) * (1 + rate)   // recursive case
```

That reads almost exactly like the financial definition, which is recursion's real
benefit: the code states the rule rather than the bookkeeping.

The base case is what makes it terminate. Without `years == 0` returning a value,
the method would recurse until the stack overflowed.

### Time complexity

**O(n)** where n is the number of years, and **O(n) space** for the call stack.

`futureValue(pv, rate, 5)` makes 6 calls — one per year plus the base case. The
measured counts confirm the linear growth:

```
years=5   calls=6
years=10  calls=11
years=20  calls=21
years=40  calls=41
```

Each call does constant work and reduces `years` by exactly one, so the call count
is n + 1.

### Optimising the recursive solution

This recursion is already linear, so there is no repeated work to eliminate — each
year is computed once. Three genuine improvements:

1. **Iteration** (`futureValueIterative`) — same O(n) time but O(1) space, no stack
   frames and no overflow risk on long horizons. Java has no tail-call optimisation,
   so deep recursion is a real constraint, not a theoretical one.

2. **Memoisation** (`futureValueVaryingRates`) — caches each year's result. It does
   not help the single-rate case, but it matters as soon as a forecast branches and
   revisits the same year repeatedly. Turns exponential recomputation into O(n).

3. **Closed form** — for a fixed rate the whole thing collapses to
   `presentValue * Math.pow(1 + rate, years)`, which is O(1). Recursion is the
   clearer expression of the concept; the formula is what production code should use
   when the rate never varies.

The classic case where memoisation is essential is naive Fibonacci, which recomputes
the same subproblems and costs O(2^n). This forecast avoids that shape because each
call spawns exactly one child, not two.
