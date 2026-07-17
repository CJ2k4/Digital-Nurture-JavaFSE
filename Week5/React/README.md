# ReactJS — Digital Nurture (Deepskilling) Hands-on Lab Solutions

Solutions for all 19 ReactJS Hands-on Labs (`ReactJS-HOL`). Each exercise is a
self-contained Create React App project in its own folder.

## How to run any exercise

```bash
cd <exercise-folder>      # e.g. cd 01-myfirstreact
npm install               # first time only
npm start                 # opens http://localhost:3000
```

For the two testing exercises (18 & 19), run the unit tests instead:

```bash
cd 18-cohorttestingapp    # or 19-gitclientapp
npm install
npm test                  # (CI=true npm test -- --watchAll=false  to run once)
```

## Exercise index

| #  | App / Folder                | Concept covered |
|----|-----------------------------|-----------------|
| 1  | `01-myfirstreact`           | First app; render a heading |
| 2  | `02-studentapp`             | Class components (Home / About / Contact) |
| 3  | `03-scorecalculatorapp`     | Function component + props + CSS |
| 4  | `04-blogapp`                | Lifecycle hooks (`componentDidMount`, `componentDidCatch`) + Fetch API |
| 5  | `05-cohortstyling`          | CSS Modules + conditional inline styling |
| 6  | `06-trainersapp`            | React Router (`BrowserRouter`, `Routes`, `Route`, `Link`, `useParams`) |
| 7  | `07-shoppingapp`            | Props; looping over an array of components |
| 8  | `08-counterapp`             | State; constructor; event-driven counters |
| 9  | `09-cricketapp`             | ES6 — `map`, arrow functions, destructuring, spread/merge |
| 10 | `10-officespacerentalapp`   | JSX elements, attributes, conditional colors |
| 11 | `11-eventexamplesapp`       | Event handling, synthetic events, CurrencyConvertor |
| 12 | `12-ticketbookingapp`       | Conditional rendering (login/logout) |
| 13 | `13-bloggerapp`             | Conditional rendering — multiple techniques |
| 14 | `14-employeecontextapp`     | Context API (`createContext`, `Provider`, `useContext`) |
| 15 | `15-ticketraisingapp`       | Controlled forms + submit handling |
| 16 | `16-mailregisterapp`        | Form validation (onChange + onSubmit) |
| 17 | `17-fetchuserapp`           | Consuming a REST API with `fetch` |
| 18 | `18-cohorttestingapp`       | Unit testing with Jest + Enzyme (mount/shallow/find/snapshot) |
| 19 | `19-gitclientapp`           | Axios + Jest mocking/spies |

## Notes

- Exercises 1–17 & 19 use React 18. Exercise 18 uses React 17 because the
  Enzyme adapter required by the lab targets React 17.
- Data-driven labs (5, 6, 18) include mock data files as the original labs
  supplied a starter project/zip.
- `node_modules` is intentionally not committed — run `npm install` per app.
