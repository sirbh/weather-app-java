# MediaFinderPro

### Branch Naming Rules

A git branch should start with a category. Pick one of these: `feature`, `bugfix`, `hotfix`, or `test`.

eg. `git branch <category>/<ticket heading on board>-<ticket no. on borad>`

* If you need to add a feature: `git branch feature/add-event-listner-42`.
* If you need to fix a bug: `git branch bugfix/button-not-displaying-342`
* If you need to fix a bug really fast (possibly with a temporary solution): `git branch hotfix/registration-form-not-working-232`
* If you need to experiment outside of an issue/ticket: `git branch test/http-client`
