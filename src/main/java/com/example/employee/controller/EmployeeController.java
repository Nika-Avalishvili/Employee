package com.example.employee.controller;

import com.example.employee.model.EmployeeDTO;
import com.example.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/employee")
    public @ResponseBody EmployeeDTO addEmployee(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.createAndUpdateEmployee(employeeDTO);
    }

    @PutMapping("/employee")
    public EmployeeDTO updateEmployee(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.createAndUpdateEmployee(employeeDTO);
    }

    @GetMapping("/employee")
    public List<EmployeeDTO> getAllEmployees(){
        return employeeService.findAllEmployees();
    }

    @GetMapping("/employee/{id}")
    public EmployeeDTO findById(@PathVariable Long id) {
        return employeeService.findEmployeeById(id);
    }

    @DeleteMapping("/employee")
    public void deleteEmployees(@RequestParam (value = "id") Long id) {
        employeeService.deleteEmployee(id);
    }
}
