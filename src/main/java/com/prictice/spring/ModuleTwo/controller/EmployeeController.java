package com.prictice.spring.ModuleTwo.controller;


import com.prictice.spring.ModuleTwo.DTO.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {


    @GetMapping(path = "/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable Long id){
        return new EmployeeDTO(id,"vijay","vijay@gamil.com",23, LocalDate.of(2024,10,2),true);
    }

    @GetMapping()
    public String getAllEmployees(@RequestParam Integer age){
     return "the age is "+age;
    }
}
