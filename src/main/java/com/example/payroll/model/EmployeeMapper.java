package com.example.payroll.model;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {
    public EmployeeDTO entityToDto(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setFirst_name(employee.getFirst_name());
        employeeDTO.setLast_name(employee.getLast_name());
        employeeDTO.setDepartment(employee.getDepartment());
        employeeDTO.setPositions(employee.getPositions());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setIs_active(employee.getIs_active());
        employeeDTO.setIs_pensions_payer(employee.getIs_pensions_payer());
        return employeeDTO;
    }

    public List<EmployeeDTO> entityToDto(List<Employee> employees){
        return employees.stream().map( x -> entityToDto(x)).collect(Collectors.toList());
    }

    public Employee dtoToEntity(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setFirst_name(employeeDTO.getFirst_name());
        employee.setLast_name(employeeDTO.getLast_name());
        employee.setDepartment(employeeDTO.getDepartment());
        employee.setPositions(employeeDTO.getPositions());
        employee.setEmail(employeeDTO.getEmail());
        employee.setIs_active(employeeDTO.getIs_active());
        employee.setIs_pensions_payer(employeeDTO.getIs_pensions_payer());
        return employee;
    }

    public List<Employee> dtoToEntity(List<EmployeeDTO> employeeDTO){
        return employeeDTO.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
    }
}
