package com.org.pcf.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @RequestMapping("/pcfdemo")
    public String pcfDemo() {
        return "Sucessfully Demonstrated, Now Testing Crucible!!";
    }

}
