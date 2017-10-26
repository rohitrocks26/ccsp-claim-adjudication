package com.ccsp.productdisposition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@ComponentScan({"com.ccsp.productdisposition","com.ccsp.event.store","com.ccsp.commons"})
@EnableKafka
public class ProductDispositionMain {

    public static void main(final String[] args) {
    	SpringApplication.run(ProductDispositionMain.class, args);
    }

}
