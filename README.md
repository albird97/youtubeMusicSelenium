Build using Selenium, Java, Maven, TestNG, etc.
---

## pre-requisite
* Java
* Maven
* Browser [Chrome / Firefox / Safari]

## Guidelines
* Clone the github repo
* Make sure you mark project directory as `sources root`. You can do it by right clicking on the "selenium" folder on the intellij > "Mark Directory as" > "Resources Root" 
* Import all required dependency, you can `click enable auto import popup if you use Intellij`  or use `mvn clean install` on terminal
* Make sure set the `Config.Properties` on `selenium/Shared/Config/Config.properties`
* There are few ways to run the automation, here are some ways to run it:
  * Run the testng.xml on `selenium/testng.xml`
  * Run the specific test case on test class 
* You can find the report on `selenium/TestResults` and open using browser


#### Author
* Albert wirawan / albert.wirawan739@gmail.com