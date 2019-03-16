# number-speller
[![Build Status](https://travis-ci.com/guillermo-varela/number-speller.svg?branch=master)](https://travis-ci.com/guillermo-varela/number-speller)

This is a Spring Boot 2 application with a command line interface that allows to enter integer (or long) numbers and then it spells them as words in US english in the same order as the given numbers list.

The application only works with integer (or long) numbers, any other input would be included in the result with the following error message: *"value" is not a valid integer or long number*

## Build system
Gradle, using the wrapper to keep the version consistent.

## Programming language
Java 8

## Framework
Spring Boot 2.1.3, this way the application not only can benefit from the IoC but also from its packaging in a single ubber/fat JAR file and its ability to support multiple deployment environments (via Spring profiles).

## Logging
Just to demostrate how Spring Boot is integrated with Logback and the configrations via Spring profiles, in the file [application.yml](https://github.com/guillermo-varela/number-speller/blob/master/src/main/resources/application.yml) you can see how Logback is configured to print into the standard ouput the "DEBUG" level for the default profile (development) but for production the level is increased to INFO and the logs are being written into a file instead of console.

Also as a demonstration, the [MDC](https://logback.qos.ch/manual/mdc.html) is used to set a *stamp* on each spelling request: https://github.com/guillermo-varela/number-speller/blob/master/src/main/java/com/sonatype/numberspeller/main/NumberSpellerMain.java#L35

## How to run it in development mode?
- Clone the repository.
- Open a terminal/console and go to the project's root folder (or import it into an IDE).
- Run the **bootRun** Gradle task passing a list of numbers separated by space, for instance `./gradlew bootRun --args "0 13 85 5237 5361232510"` which would produce this outcome: `[zero, thirteen, eighty-five, five thousand two hundred thirty-seven, five billion three hundred sixty-one million two hundred thirty-two thousand five hundred ten]`
- If you want to see what happens when entering a text the try `./gradlew bootRun --args "0 test"` and the result would be: `zero, "test" is not a valid integer or long number`
- Some debug logging will be printed out on the standard output.

## How to run it in production mode?
- Clone the repository.
- Open a terminal/console and go to the project's root folder (or import it into an IDE).
- Build the project using Gradle: `./gradlew build`
- Run the application setting the production profile and a list of numbers separated by space, for instance `java -Dspring.profiles.active=prod -jar build/libs/number-speller-1.0.0-SNAPSHOT.jar 0 13 85 5237 -50` which would produce this outcome: `zero, thirteen, eighty-five, five thousand two hundred thirty-seven, minus fifty`
- If you want to see what happens when entering a text the try `java -Dspring.profiles.active=prod -jar build/libs/number-speller-1.0.0-SNAPSHOT.jar 0 test` and the result would be: `zero, "test" is not a valid integer or long number`
- Some info logging will be written in the file `logs/app.log`.

## About the code
The entry point is the class [NumberSpellerMain](https://github.com/guillermo-varela/number-speller/blob/master/src/main/java/com/sonatype/numberspeller/main/NumberSpellerMain.java). There the user input is received.

Then it's sent to an implementation of the interface [NumberSpellerService](https://github.com/guillermo-varela/number-speller/blob/master/src/main/java/com/sonatype/numberspeller/service/NumberSpellerService.java) instantiated and assigned by Spring.

The current implementation is [NumberSpellerServiceImpl](https://github.com/guillermo-varela/number-speller/blob/master/src/main/java/com/sonatype/numberspeller/service/impl/NumberSpellerServiceImpl.java) where the input is first validated if it's empty and then tries to spell each item as a number in words using a Java 8 Stream to map each input to it's corresponding spelling or a message error if the element is not an integer o long. This way each result goes in the same order as the input items.

There, the library used to spell the numbers as words is [ICU4J](http://site.icu-project.org/home/why-use-icu4j) which has [a good pace of updates](https://mvnrepository.com/artifact/com.ibm.icu/icu4j) and even works with other languages.

## Unit tests
Those can be found at: [NumberSpellerServiceImplTest](https://github.com/guillermo-varela/number-speller/blob/master/src/test/java/com/sonatype/numberspeller/service/impl/NumberSpellerServiceImplTest.java)

The results are available on Travis CI: https://travis-ci.com/guillermo-varela/number-speller

If you wanna run the tests locally, just run `./gradlew test` and the results will be available at `build/reports/tests/test/index.html`

![Unit Tests](https://i.imgur.com/bvNt8iU.jpg)
