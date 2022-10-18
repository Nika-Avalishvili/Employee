package com.example.payroll.service;

import com.example.payroll.model.Employee;
import com.example.payroll.model.EmployeeDTO;
import com.example.payroll.model.EmployeeMapper;
import com.example.payroll.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeDTO createAndUpdateEmployee(EmployeeDTO employeeDTO){
        Employee employee = employeeMapper.dtoToEntity(employeeDTO);
        employeeRepository.save(employee);
        return employeeMapper.entityToDto(employee);
    }

    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }

    public List<EmployeeDTO> findAllEmployees(){
        List<Employee> findAll = employeeRepository.findAll();
        return employeeMapper.entityToDto(findAll);
    }

    public EmployeeDTO findEmployeeById(Long id){
        Employee employee = employeeRepository.findById(id).orElse(null);
        return employeeMapper.entityToDto(employee);
    }

}
