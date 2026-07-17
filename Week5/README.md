# Week 5 — Single Page Application framework: React

Mandatory hands-on per `DN - Java FSE Mandatory hands-on detail.xlsx`.

The sheet lists ten mandatory `ReactJS-HOL` documents — 1 to 5 and 9 to 13. All ten
were already implemented. `React/` holds all 19 HOL solutions, so the additional
hands-on (6, 7, 8, 14 to 17) are covered too.

| HOL | App | Concept |
| --- | --- | --- |
| 1 | `React/01-myfirstreact` | First app, SPA and React basics |
| 2 | `React/02-studentapp` | Class components |
| 3 | `React/03-scorecalculatorapp` | Function components, props |
| 4 | `React/04-blogapp` | Component life cycle hooks |
| 5 | `React/05-cohortstyling` | CSS Modules and inline styles |
| 9 | `React/09-cricketapp` | ES6 — let/const, arrow functions, map, filter |
| 10 | `React/10-officespacerentalapp` | JSX and rendering to the DOM |
| 11 | `React/11-eventexamplesapp` | React events, synthetic events |
| 12 | `React/12-ticketbookingapp` | Conditional rendering |
| 13 | `React/13-bloggerapp` | Lists and keys |

Full index of all 19, including the additional hands-on: `React/README.md`.

## Run any exercise

```bash
cd React/01-myfirstreact     # or any other app
npm install
npm start                    # http://localhost:3000
```

For the testing exercises (18 and 19):

```bash
cd React/18-cohorttestingapp
npm install
CI=true npm test -- --watchAll=false
```

## Toolchain note

Each app is a Create React App project on `react-scripts` 5.0.1 with React 18.

On recent Node versions, `npm run build` can fail before compiling with:

```
[eslint] package.json » eslint-config-react-app/jest#overrides[0]:
	Environment key "jest/globals" is unknown
```

This is a CRA tooling issue, not an application error — it comes from
`eslint-config-react-app/jest` and the `eslint-plugin-jest` version npm resolves,
not from any app code. The build succeeds with the linter turned off:

```bash
DISABLE_ESLINT_PLUGIN=true npm run build
```

Verified on Node v25.2.1 / npm 11.6.2: `01-myfirstreact` compiles to an optimised
production build cleanly once the lint plugin is bypassed.
