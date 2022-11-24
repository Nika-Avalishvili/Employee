package com.example.employee.service;

import com.example.employee.exceptionHandler.EmployeeNotFoundException;
import com.example.employee.model.Employee;
import com.example.employee.model.EmployeeDTO;
import com.example.employee.model.EmployeeMapper;
import com.example.employee.rabbitMQ.EmployeeConfig;
import com.example.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final RabbitTemplate template;

    public EmployeeDTO createAndUpdateEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.dtoToEntity(employeeDTO);
        employeeRepository.save(employee);
        template.convertAndSend(EmployeeConfig.EXCHANGE, EmployeeConfig.ROUTING_KEY, employeeDTO);
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
