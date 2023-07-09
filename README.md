<h1 align="center">
Hi 👋, I'm Nikita Demin

Senior Manual QA, Senior Automation QA, SDET

This is my UI test with my test site (http://ndemin.qa.tilda.ws/)
</h1>

## Tech stack:
* Java
* Junit
* Selenide
* PageObject

## How to launch:

Press `Run Test` in "UITest" (src/test/java/UITest.java)

## How to turn on/off parrallel launch:

Write `true/false` in "junit.jupiter.execution.parallel.enabled" (src/test/resources/junit-platform.properties)

## Test cases:

*  **Check Main page** (`checkMainPage`)
1) open main page
2) check head name
3) check for main photo
4) check quantity of side buttons

*  **Check Registration page** (`checkRegistrationPage`)
1) open main page -> open registration page
2) check head name
3) sign up with 2 different accounts
4) check success of registration

*  **Check Files page** (`checkFilesPage`)
1) open main page -> open files page
2) download and check image
3) download and check txt file
4) download and check excel file

* **Check Text page** (`checkTextPage`)
1)  open main page -> open text page
2) check text elements (types of testing)

*  **Check Contacts page** (`checkContactsPage`)
1) open main page -> open contacts page
2) check the correctness of the text and the presence of links