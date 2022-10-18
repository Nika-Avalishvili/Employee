package com.example.payroll.Services;

import com.example.payroll.Models.Employees;
import com.example.payroll.Models.EmployeesDTO;
import com.example.payroll.Models.EmployeesMapper;
import com.example.payroll.Repositories.EmployeesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeesService {
    private final EmployeesRepository employeesRepository;
    private final EmployeesMapper employeesMapper;

    public EmployeesDTO createEmployee(EmployeesDTO employeesDTO){
        Employees employees = employeesMapper.dtoToEntity(employeesDTO);
        employeesRepository.save(employees);
        return employeesMapper.entityToDto(employees);
    }

    public EmployeesDTO updateEmployee(EmployeesDTO employeesDTO){
        Employees employees = employeesMapper.dtoToEntity(employeesDTO);
        employeesRepository.save(employees);
        return employeesMapper.entityToDto(employees);
    }

    public List<EmployeesDTO> findAllEmployees(){
        List<Employees> findAll = employeesRepository.findAll();
        return employeesMapper.entityToDto(findAll);
    }

    public EmployeesDTO findEmployeeById(Long id){
        Employees employees = employeesRepository.findById(id).orElse(null);
        return employeesMapper.entityToDto(employees);
    }

}
