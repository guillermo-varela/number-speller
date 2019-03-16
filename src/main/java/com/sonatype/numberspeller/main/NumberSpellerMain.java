package com.sonatype.numberspeller.main;

import com.sonatype.numberspeller.service.NumberSpellerService;
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
        System.out.println(service.spell(args));
    }
}
