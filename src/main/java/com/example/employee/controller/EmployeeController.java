package com.example.employee.controller;

import com.example.employee.model.EmployeeDTO;
import com.example.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public @ResponseBody EmployeeDTO addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.createAndUpdateEmployee(employeeDTO);
    }

    @PutMapping
    public EmployeeDTO updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.createAndUpdateEmployee(employeeDTO);
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.findAllEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeDTO findById(@PathVariable Long id) {
        return employeeService.findEmployeeById(id);
    }

    @DeleteMapping
    public void deleteEmployee(@RequestParam(value = "id") Long id) {
        employeeService.deleteEmployee(id);
    }
}
