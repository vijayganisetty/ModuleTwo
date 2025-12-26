package com.prictice.spring.ModuleTwo.controller;


import com.prictice.spring.ModuleTwo.DTO.EmployeeDTO;
import com.prictice.spring.ModuleTwo.entity.EmployeeEntity;
import com.prictice.spring.ModuleTwo.repository.EmployeeRepository;
import com.prictice.spring.ModuleTwo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping(path = "/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }

    @GetMapping()
    public List<EmployeeDTO> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @PostMapping()
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employee){
            return employeeService.createEmployee(employee);
    }
}
