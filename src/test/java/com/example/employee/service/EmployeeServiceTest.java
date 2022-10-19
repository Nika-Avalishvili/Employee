package com.example.employee.service;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;

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
        EmployeeDTO employeeDTO = new EmployeeDTO(15L, "Ilia","Tchavtchavadze", "Education", "Founder of education council", "ilia.tchavtchavadze@gmail.com", true, true);

        Mockito.when(employeeRepository.save(any())).thenAnswer( invocationOnMock -> invocationOnMock.getArgument(0));

        EmployeeDTO actualDTO = employeeService.createAndUpdateEmployee(employeeDTO);

        Assertions.assertEquals(employeeDTO, actualDTO);


    }

    @Test
    void findAllEmployees() {
        Assertions.assertFalse(employeeService.findAllEmployees().isEmpty());
        Assertions.assertEquals(1,employeeService.findAllEmployees().size());
    }


    @Test
    void findEmployeeById() {
        Assertions.assertTrue(employeeService.findEmployeeById(15L).getFirst_name().equals("Ilia"));
        Assertions.assertTrue(employeeService.findEmployeeById(15L).getLast_name().equals("Tchavtchavadze"));
        Assertions.assertTrue(employeeService.findEmployeeById(15L).getDepartment().equals("Education"));
        Assertions.assertTrue(employeeService.findEmployeeById(15L).getPositions().equals("Founder of education council"));
        Assertions.assertTrue(employeeService.findEmployeeById(15L).getEmail().equals("ilia.tchavtchavadze@gmail.com"));
        Assertions.assertTrue(employeeService.findEmployeeById(15L).getIs_active().equals(true));
        Assertions.assertTrue(employeeService.findEmployeeById(15L).getIs_pensions_payer().equals(true));
    }

    @Test
    void deleteEmployee() {
        employeeService.deleteEmployee(15L);

        Assertions.assertFalse(employeeService.findAllEmployees().stream()
                .anyMatch(employee -> employee.getFirst_name().equals("Ilia") &&
                        employee.getLast_name().equals("Tchavtchavadze") &&
                        employee.getDepartment().equals("Education") &&
                        employee.getPositions().equals("Founder of education council") &&
                        employee.getEmail().equals("ilia.tchavtchavadze@gmail.com") &&
                        employee.getIs_active().equals(true) &&
                        employee.getIs_pensions_payer().equals(true)));
    }

}