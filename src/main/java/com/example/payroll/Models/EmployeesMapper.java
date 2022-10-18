package com.example.payroll.Models;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeesMapper {
    public EmployeesDTO entityToDto(Employees employees){
        EmployeesDTO employeesDTO = new EmployeesDTO();
        employeesDTO.setEmployee_id(employees.getEmployee_id());
        employeesDTO.setFirst_name(employees.getFirst_name());
        employeesDTO.setLast_name(employees.getLast_name());
        employeesDTO.setDepartment(employees.getDepartment());
        employeesDTO.setPositions(employees.getPositions());
        employeesDTO.setEmail(employees.getEmail());
        employeesDTO.setEmployee_status(employees.getEmployee_status());
        employeesDTO.setPf_status(employees.getPf_status());
        return employeesDTO;
    }

    public List<EmployeesDTO> entityToDto(List<Employees> employees){
        return employees.stream().map( x -> entityToDto(x)).collect(Collectors.toList());
    }

    public Employees dtoToEntity(EmployeesDTO employeesDTO){
        Employees employees = new Employees();
        employees.setEmployee_id(employeesDTO.getEmployee_id());
        employees.setFirst_name(employeesDTO.getFirst_name());
        employees.setLast_name(employeesDTO.getLast_name());
        employees.setDepartment(employeesDTO.getDepartment());
        employees.setPositions(employeesDTO.getPositions());
        employees.setEmail(employeesDTO.getEmail());
        employees.setEmployee_status(employeesDTO.getEmployee_status());
        employees.setPf_status(employeesDTO.getPf_status());
        return employees;
    }

    public List<Employees> dtoToEntity(List<EmployeesDTO> employeesDTO){
        return employeesDTO.stream().map( x -> dtoToEntity(x)).collect(Collectors.toList());
    }
}
