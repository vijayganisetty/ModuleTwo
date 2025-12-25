package com.prictice.spring.ModuleTwo.controller;


import com.prictice.spring.ModuleTwo.entity.EmployeeEntity;
import com.prictice.spring.ModuleTwo.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @GetMapping(path = "/{id}")
    public EmployeeEntity getEmployeeById(@PathVariable Long id){
        return employeeRepository.findById(id).orElse(null);
    }

    @GetMapping()
    public List<EmployeeEntity> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @PostMapping()
    public EmployeeEntity getEmployeeById(@RequestBody EmployeeEntity employee){
            return employeeRepository.save(employee);
    }
}
