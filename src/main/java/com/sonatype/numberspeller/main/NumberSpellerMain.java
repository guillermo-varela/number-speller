package com.sonatype.numberspeller.main;

import com.sonatype.numberspeller.service.NumberSpellerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

import java.util.UUID;

@SpringBootApplication
@ComponentScan("com.sonatype.numberspeller")
public class NumberSpellerMain implements CommandLineRunner {

    private static Logger LOGGER = LoggerFactory.getLogger(NumberSpellerMain.class);

    @Autowired
    private NumberSpellerService service;

    public static void main(String[] args) {
        new SpringApplicationBuilder(NumberSpellerMain.class)
                .web(WebApplicationType.NONE)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }

    @Override
    public void run(String... args) {
        MDC.put("stamp", UUID.randomUUID().toString());
        try {
            System.out.println(service.spell(args));
        } catch (Exception e) {
            // Shouldn't happen, but just in case :)
            LOGGER.error("Error spelling the input", e);
            System.err.println("Ops! An error just occurred: " + e.getMessage());
        }
        // No need to clear the MDC because the execution ends here
    }
}
