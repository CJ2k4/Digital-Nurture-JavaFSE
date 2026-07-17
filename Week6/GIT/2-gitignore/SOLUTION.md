# Git HOL 2 – .gitignore

## Task

Ignore all `.log` files and any `log` folder so they are not committed.

## Solution

Create or update `.gitignore`:

```
*.log
log/
```

## Commands to verify

```bash
cd GitDemo   # or your working repo
touch app.log
mkdir -p log
echo "ignored" > log/debug.log
git status
```

Expected: `app.log` and the `log/` directory should **not** appear as untracked files.

```bash
git add .gitignore
git commit -m "Ignore log files and log directory"
git status
```
