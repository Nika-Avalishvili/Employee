package com.example.employee.service;

import com.example.employee.model.Employee;
import com.example.employee.model.EmployeeDTO;
import com.example.employee.model.EmployeeMapper;
import com.example.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    EmployeeRepository employeeRepository;
    EmployeeMapper employeeMapper;
    EmployeeService employeeService;

    @BeforeEach
    void setUp(){
        employeeMapper = new EmployeeMapper();
        employeeService = new EmployeeService(employeeRepository, employeeMapper);
    }

    @Test
    void createAndUpdateEmployee() {
        EmployeeDTO employeeDTO = new EmployeeDTO(1L, "Ilia","Tchavtchavadze", "Education", "Founder of education council", "ilia.tchavtchavadze@gmail.com", true, true);
        Mockito.when(employeeRepository.save(any())).thenAnswer( invocationOnMock -> invocationOnMock.getArgument(0));

        EmployeeDTO actualDTO = employeeService.createAndUpdateEmployee(employeeDTO);

        Assertions.assertEquals(employeeDTO, actualDTO);
        System.out.println("Create and Update test passed successfully!");
    }

    @Test
    void findAllEmployees() {
        Employee emp1 = new Employee(5L,"Nika","Avalishvili","D1","P1","as@gmail.com",true, true);
        Employee emp2 = new Employee(6L,"Giorgi","Margvelashvili","D2","P2","as@gmail.com",true, true);
        Mockito.when(employeeRepository.findAll()).thenReturn(List.of(emp1,emp2));


        Assertions.assertEquals(2, employeeService.findAllEmployees().size());
        System.out.println("Find All Employees test passed successfully!");
    }


    @Test
    void findEmployeeById() {
        Employee emp1 = new Employee(5L,"Nika","Avalishvili","D1","P1","as@gmail.com",true, true);
        Employee emp2 = new Employee(6L,"Giorgi","Margvelashvili","D2","P2","as@gmail.com",true, true);

        Mockito.when(employeeRepository.findById(anyLong())).thenAnswer(invocationOnMock -> Stream.of(emp1, emp2).filter(e -> e.getId().equals(invocationOnMock.getArgument(0))).findFirst());

        Assertions.assertEquals("Avalishvili",employeeService.findEmployeeById(5L).getLastName());
        Assertions.assertEquals("Giorgi",employeeService.findEmployeeById(6L).getFirstName());

        System.out.println("Find Employee by id test passed successfully!");
    }

    @Test
    void deleteEmployee() {
        Employee emp1 = new Employee(5L,"Nika","Avalishvili","D1","P1","as@gmail.com",true, true);
        employeeService.deleteEmployee(5L);
        Mockito.verify(employeeRepository,times(1)).deleteById(5L);

        System.out.println("Delete Employee test passed successfully!");
    }

}