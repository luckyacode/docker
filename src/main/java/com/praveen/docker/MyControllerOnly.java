package com.praveen.docker;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controllerOnly")
public class MyControllerOnly {

    @PutMapping("/put")
    public String testPut() {
        return "Test Put";
    }

}
