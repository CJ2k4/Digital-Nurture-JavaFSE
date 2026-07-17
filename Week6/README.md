# Week 6 — Version Control with Git

Mandatory hands-on per `DN - Java FSE Mandatory hands-on detail.xlsx`.

All five `Git-HOL` documents are mandatory, and all five were already solved.

| HOL | Folder | Topic |
| --- | --- | --- |
| 1 | `GIT/1-git-setup` | `git init`, `status`, `add`, `commit`, `push`, `pull`, and Git configuration |
| 2 | `GIT/2-gitignore` | Ignoring unwanted files with `.gitignore` |
| 3 | `GIT/3-branching-merging` | Branching and merging |
| 4 | `GIT/4-conflict-resolution` | Resolving a merge conflict |
| 5 | `GIT/5-cleanup-push` | Clean up and push back to remote |

Each folder has a `SOLUTION.md` with the commands, plus the files the lab works on.

## Lab artifacts

The conflict-resolution lab keeps all three states of the file, so the exercise can be
replayed and the resolution inspected:

| File | Contents |
| --- | --- |
| `hello.xml.branch` | `Hello from GitWork branch` |
| `hello.xml.master` | `Hello from master` |
| `hello.xml.resolved` | `Hello from merged branch and master` |

`GIT/2-gitignore/.gitignore` is the artifact of HOL 2, not configuration for this
repository — the repository's own ignore rules are in the root `.gitignore`.
