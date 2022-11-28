package com.example.employee.service;

import com.example.employee.exceptionHandler.EmployeeNotFoundException;
import com.example.employee.model.Employee;
import com.example.employee.model.EmployeeDTO;
import com.example.employee.model.EmployeeMapper;
import com.example.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final StreamBridge streamBridge;



    public EmployeeDTO createAndUpdateEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.dtoToEntity(employeeDTO);
        employeeRepository.save(employee);
        streamBridge.send("employee-out-0", employeeDTO);
        return employeeMapper.entityToDto(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public List<EmployeeDTO> findAllEmployees() {
        List<Employee> findAll = employeeRepository.findAll();
        return employeeMapper.entityToDto(findAll);
    }

    public EmployeeDTO findEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(EmployeeNotFoundException::new);
        return employeeMapper.entityToDto(employee);
    }

}
