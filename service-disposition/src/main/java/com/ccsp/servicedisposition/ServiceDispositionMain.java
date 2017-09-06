package com.ccsp.servicedisposition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@ComponentScan({"com.ccsp.servicedisposition","com.ccsp.event.store","com.ccsp.commons"})
@EnableKafka
public class ServiceDispositionMain {

    public static void main(final String[] args) {
    	SpringApplication.run(ServiceDispositionMain.class, args);
    }

}
