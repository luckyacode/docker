package com.praveen.docker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping
    public String start(){
        return "Docker is running successfully!";
    }

    @GetMapping("/test")
    public String test(){
        return "Test endpoint is working!";
    }

}
