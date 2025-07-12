package com.praveen.docker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    EmployeesRepository employeesRepository;

    @GetMapping
    public String start() {
        return "Docker is running successfully!";
    }

    @GetMapping("/test")
    public String test() {
        return "Test endpoint is working!";
    }

    @GetMapping("/save")
    public String save() {
        Employees employee = Employees.builder().name("John").lastName("Doe").age(30).salary(50000.0).department("Engineering").dob("1993-01-01").email("myemail.com").status("Active").build();
        employeesRepository.save(employee);
        return "Successfully saved employee data!";
    }

    @GetMapping("/get")
    public List get() {
        return employeesRepository.findAll();
    }

}
