# Git HOL 4 – Merge Conflict Resolution

## Setup branch with hello.xml

```bash
git status                    # verify clean master/main
git checkout -b GitWork
```

Create `hello.xml` on the branch (see `hello.xml.branch` in this folder):

```bash
cp hello.xml.branch hello.xml
git add hello.xml
git commit -m "Add hello.xml on GitWork branch"
```

## Create conflicting change on master

```bash
git checkout main
cp hello.xml.master hello.xml
git add hello.xml
git commit -m "Add hello.xml on master with different content"
git log --oneline --graph --decorate --all
git diff main GitWork
```

## Merge and resolve conflict

```bash
git merge GitWork
# Conflict markers appear in hello.xml — resolve manually or with mergetool:
git mergetool
```

Resolved `hello.xml` (see `hello.xml.resolved`):

```xml
<?xml version="1.0" encoding="UTF-8"?>
<greeting>
    <message>Hello from merged branch and master</message>
</greeting>
```

```bash
git add hello.xml
git commit -m "Resolve merge conflict in hello.xml"
git status
```

## Add backup files to .gitignore

```bash
echo "*.orig" >> .gitignore
git add .gitignore
git commit -m "Ignore merge backup files"
git branch -a
git branch -d GitWork
git log --oneline --graph --decorate
```
