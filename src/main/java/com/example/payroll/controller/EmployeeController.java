package com.example.payroll.controller;

import com.example.payroll.model.EmployeeDTO;
import com.example.payroll.repository.EmployeeRepository;
import com.example.payroll.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeeController {


    private final EmployeeService employeeService;

    private final EmployeeRepository employeeRepository;

    @PostMapping("/employees")
    public @ResponseBody EmployeeDTO addEmployee(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.createAndUpdateEmployee(employeeDTO);
    }

    @PutMapping("/employees")
    public EmployeeDTO updateEmployee(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.createAndUpdateEmployee(employeeDTO);
    }

    @GetMapping("/employees")
    public List<EmployeeDTO> getAllEmployees(){
        return employeeService.findAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public EmployeeDTO findById(@PathVariable Long id) {
        return employeeService.findEmployeeById(id);
    }

    @DeleteMapping("/employees")
    public void deleteEmployees(@RequestParam (value = "id") Long id) {
        employeeService.deleteEmployee(id);
    }
}
