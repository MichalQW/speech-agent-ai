
# Contributing to the Project
Thank you for your interest in our project. To start collaborating, please follow the guidelines provided below.

# Forking the Repository
The first step is to fork the repository. To do this, you need to:
1. Click the Fork button at the top-right of the repository page on GitHub.
2. Clone your fork locally:\
   ``git clone https://github.com/YOUR_USERNAME/PROJECT_NAME.git``
3. Add the upstream repository:\
   ``git remote add upstream https://github.com/ORIGINAL_OWNER/PROJECT_NAME.git``
4. Ensure your fork stays up to date:
    ~~~
    git fetch upstream 
    git merge upstream/main
    ~~~
# Creating a Pull Request
When you finish completing the task, you need to create a pull request. We distinguish six types of branch naming:
- Feature
- Fix
- Docs
- Chore
- Refactor
- Test
  You can find the guidelines for commit semantics below. To create a new pull request:
1. Create a new branch for your changes:\
   ```git checkout -b feature/short-description```
2. Make your changes and commit using [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/):\
   ```git commit -m "feat: short and meaningful commit message"```
3. Push your branch:\
   ```git push origin feature/short-description```
4. Open a Pull Request on GitHub:
- **Title:** Use the format: ```[TYPE] Short description```
    * Example: ```[Feature] Add support for new API endpoint```
- **Description:**
    * What problem does this PR solve?
    * What changes were made?
    * Any additional context or dependencies?
- **Link to issue:** If applicable, mention it using ```Closes #ISSUE_NUMBER```.
# Commit Guidelines
Follow the [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/) format:\
```feat:``` – New feature\
```fix:``` – Bug fix\
```docs:``` – Documentation updates\
```chore:``` – Maintenance or tooling changes\
```refactor:``` – Code restructuring without functional changes\
```test:``` – Adding or updating tests