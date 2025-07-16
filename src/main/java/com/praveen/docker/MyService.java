package com.praveen.docker;

import org.springframework.stereotype.Service;

@Service
public class MyService {

    public String testGet() {
        return "Test Get";
    }

    public String testPost() {
        return "Test Post";
    }

    public String testPut() {
        return "Test Put";
    }

    public String testDelete() {
        return "Test Delete";
    }

    public static String testStatic(){
        return "Static is tested";
    }

    private String testPrivate(){
        return "Private tested";
    }

}
