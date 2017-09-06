package com.ccsp.accums;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@ComponentScan({"com.ccsp.accums","com.ccsp.event.store","com.ccsp.commons"})
@EnableKafka
public class AccumsMain {

	public static void main(final String[] args) {
    	SpringApplication.run(AccumsMain.class, args);
    }


}
