package com.example.employee.model;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {
    public EmployeeDTO entityToDto(Employee employee){
        return new EmployeeDTO.EmployeeDTOBuilder(
                employee.getId(),
                employee.getFirst_name(),
                employee.getLast_name(),
                employee.getDepartment(),
                employee.getPositions(),
                employee.getEmail(),
                employee.getIs_active(),
                employee.getIs_pensions_payer())
                .buildEmployeeDTO();
    }

    public List<EmployeeDTO> entityToDto(List<Employee> employees){
        return employees.stream().map( x -> entityToDto(x)).collect(Collectors.toList());
    }

    public Employee dtoToEntity(EmployeeDTO employeeDTO){
        return new Employee.EmployeeBuilder(
                                employeeDTO.getId(),
                                employeeDTO.getFirst_name(),
                                employeeDTO.getLast_name(),
                                employeeDTO.getDepartment(),
                                employeeDTO.getPositions(),
                                employeeDTO.getEmail(),
                                employeeDTO.getIs_active(),
                                employeeDTO.getIs_pensions_payer()
                                ).buildEmployee();
    }

    public List<Employee> dtoToEntity(List<EmployeeDTO> employeeDTO){
        return employeeDTO.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
    }
}
