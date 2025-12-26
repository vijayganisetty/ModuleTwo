package com.prictice.spring.ModuleTwo.service;

import com.prictice.spring.ModuleTwo.DTO.EmployeeDTO;
import com.prictice.spring.ModuleTwo.entity.EmployeeEntity;
import com.prictice.spring.ModuleTwo.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeeDTO getEmployeeById(Long id) {
        EmployeeEntity employee = employeeRepository.findById(id).orElse(null);
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();
        return employeeEntityList.stream()
                 .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class))
                 .collect(Collectors.toList());
    }

    public EmployeeDTO createEmployee(EmployeeDTO employee) {
        EmployeeEntity inputEmployeeEntity = modelMapper.map(employee, EmployeeEntity.class);
        EmployeeEntity outputEmployeeEntity = employeeRepository.save(inputEmployeeEntity);
        return modelMapper.map(outputEmployeeEntity, EmployeeDTO.class);
    }
}
