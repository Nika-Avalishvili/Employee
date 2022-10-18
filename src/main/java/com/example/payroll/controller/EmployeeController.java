package com.example.payroll.controller;

import com.example.payroll.model.EmployeeDTO;
import com.example.payroll.repository.EmployeeRepository;
import com.example.payroll.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/employees")
    public @ResponseBody EmployeeDTO addEmployee(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.createEmployee(employeeDTO);
    }

    @PutMapping("/employees")
    public EmployeeDTO updateEmployee(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.updateEmployee(employeeDTO);
    }

    @GetMapping("/employees")
    public List<EmployeeDTO> getAllEmployees(){
        return employeeService.findAllEmployees();
    }

    @GetMapping("/employees/{Id}")
    public EmployeeDTO findById(@PathVariable (value="Id") Long id) {
        return employeeService.findEmployeeById(id);
    }

    @DeleteMapping("/employees")
    public void deleteEmployees(@RequestParam (value = "id") Long id) {
        employeeRepository.deleteById(id);
    }
}
