# Git HOL 5 – Cleanup and Push to Remote

## Commands

```bash
git status                    # verify master/main is clean
git branch -a
git pull origin main          # or: git pull origin master
git push origin main          # or: git push origin master
```

After pushing, verify on GitLab/GitHub that:

- `hello.xml` shows the resolved merge content
- `.gitignore` includes `*.log`, `log/`, and `*.orig`
- The `GitWork` branch has been deleted locally
