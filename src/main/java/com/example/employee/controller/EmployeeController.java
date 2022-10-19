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
