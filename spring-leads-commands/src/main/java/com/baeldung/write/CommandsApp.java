package com.baeldung.write;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.baeldung.store.spring.EventsConfig;

@SpringBootApplication
public class CommandsApp {

    public static void main(final String[] args) {
        new SpringApplicationBuilder().sources(CommandsApp.class, EventsConfig.class).run(args);
    }

}
