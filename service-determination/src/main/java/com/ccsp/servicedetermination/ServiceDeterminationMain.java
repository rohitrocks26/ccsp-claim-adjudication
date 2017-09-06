package com.ccsp.servicedetermination;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@ComponentScan({"com.ccsp.servicedetermination","com.ccsp.event.store","com.ccsp.commons"})
@EnableKafka
public class ServiceDeterminationMain {

    public static void main(final String[] args) {
    	SpringApplication.run(ServiceDeterminationMain.class, args);
    }

}
