# Git HOL 1 – Setup and First Commit

## Step 1: Git configuration

```bash
git --version
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"
git config --global --list
```

## Step 2: Configure editor (Notepad++ on Windows)

```bash
# Add Notepad++ to PATH, then:
alias npp='notepad++'
git config --global core.editor "notepad++ -multiInst -notabbar -nosession -noPlugin"
git config --global -e
```

On Linux, use your preferred editor instead:

```bash
git config --global core.editor "nano"
```

## Step 3: Initialize repository and add a file

```bash
mkdir GitDemo
cd GitDemo
git init
ls -la
echo "Welcome to Git Demo" > welcome.txt
cat welcome.txt
git status
git add welcome.txt
git commit -m "Add welcome.txt with initial content"
git status
```

## Step 4: Connect to remote (GitLab/GitHub)

```bash
git remote add origin <your-remote-url>
git pull origin main --allow-unrelated-histories   # or: git pull origin master
git push -u origin main                           # or: git push origin master
```

The `welcome.txt` file in this folder is the expected result after Step 3.
