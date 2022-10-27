package com.example.employee.model;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {
    public EmployeeDTO entityToDto(Employee employee){
        EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .department(employee.getDepartment())
                .positions(employee.getPositions())
                .email(employee.getEmail())
                .isActive(employee.getIsActive())
                .isPensionsPayer(employee.getIsPensionsPayer()).build();
        return employeeDTO;
    }

    public List<EmployeeDTO> entityToDto(List<Employee> employees){
        return employees.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public Employee dtoToEntity(EmployeeDTO employeeDTO){
        Employee employee = Employee.builder()
                .id(employeeDTO.getId())
                .firstName(employeeDTO.getFirstName())
                .lastName(employeeDTO.getLastName())
                .department(employeeDTO.getDepartment())
                .positions(employeeDTO.getPositions())
                .email(employeeDTO.getEmail())
                .isActive(employeeDTO.getIsActive())
                .isPensionsPayer(employeeDTO.getIsPensionsPayer())
                .build();
        return employee;
    }

    public List<Employee> dtoToEntity(List<EmployeeDTO> employeeDTO){
        return employeeDTO.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
