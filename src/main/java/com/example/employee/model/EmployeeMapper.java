package com.example.employee.model;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {
    public EmployeeDTO entityToDto(Employee employee){
        return new EmployeeDTO.EmployeeDTOBuilder(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDepartment(),
                employee.getPositions(),
                employee.getEmail(),
                employee.getIsActive(),
                employee.getIsPensionsPayer())
                .buildEmployeeDTO();
    }

    public List<EmployeeDTO> entityToDto(List<Employee> employees){
        return employees.stream().map( x -> entityToDto(x)).collect(Collectors.toList());
    }

    public Employee dtoToEntity(EmployeeDTO employeeDTO){
        return new Employee.EmployeeBuilder(
                                employeeDTO.getId(),
                                employeeDTO.getFirstName(),
                                employeeDTO.getLastName(),
                                employeeDTO.getDepartment(),
                                employeeDTO.getPositions(),
                                employeeDTO.getEmail(),
                                employeeDTO.getIsActive(),
                                employeeDTO.getIsPensionsPayer()
                                ).buildEmployee();
    }

    public List<Employee> dtoToEntity(List<EmployeeDTO> employeeDTO){
        return employeeDTO.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
    }
}
