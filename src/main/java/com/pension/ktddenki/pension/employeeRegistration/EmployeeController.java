package com.pension.ktddenki.pension.employeeRegistration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class EmployeeController {

    private final EmployeeRepository employeeRepository;


    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @PostMapping("/test")
    public UserDTO test(@RequestBody UserDTO user) {
        return user;
    }

    @GetMapping("/employees")
    public List<EmployeeModel> getEmployees() {
        return employeeRepository.findAll();
    }
}
