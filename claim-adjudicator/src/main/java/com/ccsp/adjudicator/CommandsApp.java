package com.ccsp.adjudicator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@ComponentScan({"com.ccsp.adjudicator","com.ccsp.event.store","com.ccsp.commons"})
@EnableKafka
public class CommandsApp {

    public static void main(final String[] args) {
    	SpringApplication.run(CommandsApp.class, args);
    }

}
