# Git HOL 3 – Branching and Merging

## Branching

```bash
git checkout -b GitNewBranch
git branch -a
git checkout GitNewBranch
echo "Branch content" > branch-file.txt
git add branch-file.txt
git commit -m "Add branch-file.txt on GitNewBranch"
git status
```

## Merging

```bash
git checkout main          # or: git checkout master
git diff main GitNewBranch
git merge GitNewBranch
git log --oneline --graph --decorate
git branch -d GitNewBranch
git status
```

## Visual diff (P4Merge on Windows)

```bash
git config --global merge.tool p4merge
git mergetool -t p4merge
```

On Linux, use `meld` or `vimdiff`:

```bash
git config --global merge.tool meld
git difftool main GitNewBranch
```

The `branch-file.txt` in this folder is the file added on the branch before merging.
