package com.prictice.spring.ModuleTwo.service;

import com.prictice.spring.ModuleTwo.DTO.EmployeeDTO;
import com.prictice.spring.ModuleTwo.entity.EmployeeEntity;
import com.prictice.spring.ModuleTwo.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        return employeeRepository.findById(id).map(employee -> modelMapper.map(employee, EmployeeDTO.class));
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

    public EmployeeDTO updateEmployeeById(Long id, EmployeeDTO employeeDTO) {
        EmployeeEntity newEmployee = modelMapper.map(employeeDTO, EmployeeEntity.class);
            newEmployee.setId(id);
            return modelMapper.map(employeeRepository.save(newEmployee), EmployeeDTO.class);
    }

    public boolean deleteEmployeeById(Long id) {
        boolean exists = employeeRepository.existsById(id);
        if(exists){
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public EmployeeDTO updateEmployeePartially(Long id, Map<String, Object> updates) {
       boolean exists = employeeRepository.existsById(id);

       if(!exists) return null;

       EmployeeEntity employee = employeeRepository.findById(id).get();

       updates.forEach((field, value) -> {
           Field inputField = ReflectionUtils.getRequiredField(EmployeeEntity.class, field);
           inputField.setAccessible(true);
           ReflectionUtils.setField(inputField, employee, value);
       });

       EmployeeEntity employeeSaved = employeeRepository.save(employee);
       return modelMapper.map(employeeSaved, EmployeeDTO.class);
    }
}
