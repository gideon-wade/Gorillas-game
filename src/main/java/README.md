#JavaFX with Maven
This is a short introduction to use javaFX with maven. If you want to
run the project via the terminal, make sure that you have maven installed.
This can be done with [Chocolatey](https://chocolatey.org/install) by running the command
`choco install maven`

##Project structure
All FXML files must be located in the `resources` folder,
and their respective controller must be in the `Controller` package.
Testing must be in the folder `src.test.java`

##Install the dependencies of the project
Using JetBrains' IntelliJ, simple press on the Maven tab on the left, and the small
reload icon. One can also go to the `pom.xml` file, and the maven reload
icon will show up.

Using the terminal, simply type `mvn install`
##Run the project
Using JetBrains' IntelliJ, simple press on the Maven tab on the left.
Then open the `Plugsins` tab, and locate javafx. Press the maven goal `javafx:run`

Using the terminal, simply type `mvn javafx:run`