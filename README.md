# number-speller
This is a Spring Boot 2 application with a command line interface that allows to enter integer (or long) numbers and then it spells them as words in US english in the same order as the given numbers list.

The application only works with integer (or long) numbers, any other input would be included in the result with the following error message: *"value" is not a valid integer or long number*

## How to run it in development mode?
- Clone the repository.
- Open a terminal/console and go to the project's root folder (or import it into an IDE).
- Run the **bootRun** Gradle task passing a list of numbers separated by space, for instance `./gradlew bootRun --args "0 13 85 5237 5361232510""` which would produce this outcome: `"0," is not a valid integer or long number, "13," is not a valid integer or long number, "85," is not a valid integer or long number, "5237," is not a valid integer or long number, five billion three hundred sixty-one million two hundred thirty-two thousand five hundred ten`
- If you want to see what happens when entering a text the try `./gradlew bootRun --args "0 test"` and the result would be: `zero, "test" is not a valid integer or long number`
- Some debug logging will be printed out on the standard output.

## How to run it in development mode?
- Clone the repository.
- Open a terminal/console and go to the project's root folder (or import it into an IDE).
- Build the project using Gradle: `./gradlew build`
- Run the application setting the production profile and a list of numbers separated by space, for instance `java -Dspring.profiles.active=prod -jar build\libs\number-speller-1.0.0-SNAPSHOT.jar 0 13 85 5237` which would produce this outcome: `zero, thirteen, eighty-five, five thousand two hundred thirty-seven`
- If you want to see what happens when entering a text the try `java -Dspring.profiles.active=prod -jar build\libs\number-speller-1.0.0-SNAPSHOT.jar 0 test` and the result would be: `zero, "test" is not a valid integer or long number`
- Some info logging will be written in the file `logs/app.log`.
