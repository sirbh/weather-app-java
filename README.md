# MediaFinderPro

### Running the project

before running make sure `maven` is installed and working by running command `mvn --version`

To build the project go to the root of project `mediafinder` open terminal and run `mvn package`.

Then to run project run `java -jar target\mediafinder-1.0.one-jar.jar`

### Branch Naming Rules

A git branch should start with a category. Pick one of these: `feature`, `bugfix`, `hotfix`, or `test`.

eg. `git branch <category>/<ticket heading on board>-<ticket no. on borad>`

* If you need to add a feature: `git branch feature/add-event-listner-42`.
* If you need to fix a bug: `git branch bugfix/button-not-displaying-342`
* If you need to fix a bug really fast (possibly with a temporary solution): `git branch hotfix/registration-form-not-working-232`
* If you need to experiment outside of an issue/ticket: `git branch test/http-client`
