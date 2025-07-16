package com.praveen.docker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class Controller {
    @Autowired
    MyService myService;

    @GetMapping
    public String start() {
        return "Kubernetes is running successfully!";
    }

    @GetMapping("/test")
    public String test() {
        return "Test kubernetes docker endpoint is working!";
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseDTO<Object>> testGet() {
       return ResponseEntity.ok(ResponseDTO.builder().
               message(HttpStatus.OK.name()).
               data(myService.testGet()).
               status(HttpStatus.OK.value())
               .build());
    }

    @PostMapping("/post")
    public ResponseDTO<?> testPost() {
        return ResponseDTO.builder().
                message(HttpStatus.OK.name()).
                data(myService.testPost()).
                status(HttpStatus.OK.value())
                .build();
    }

    @PutMapping("/put")
    public String testPut() {
        return myService.testPut();
    }

    @DeleteMapping("/delete")
    public String testDelete() {
        return myService.testDelete();
    }


}
