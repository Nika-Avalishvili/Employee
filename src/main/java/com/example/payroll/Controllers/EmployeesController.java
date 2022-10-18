package com.example.payroll.Controllers;

import com.example.payroll.Models.EmployeesDTO;
import com.example.payroll.Repositories.EmployeesRepository;
import com.example.payroll.Services.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeesController {

    @Autowired
    private EmployeesService employeesService;
    @Autowired
    private EmployeesRepository employeesRepository;

    @PostMapping("/employees")
    public @ResponseBody EmployeesDTO addEmployee(@RequestBody EmployeesDTO employeesDTO){
        return employeesService.createEmployee(employeesDTO);
    }

    @PutMapping("/employees")
    public EmployeesDTO updateEmployee(@RequestBody EmployeesDTO employeesDTO){
        return employeesService.updateEmployee(employeesDTO);
    }

    @GetMapping("/employees")
    public List<EmployeesDTO> getAllEmployees(){
        return employeesService.findAllEmployees();
    }

    @GetMapping("/employees/{Id}")
    public EmployeesDTO findById(@PathVariable (value="Id") Long id) {
        return employeesService.findEmployeeById(id);
    }

    @DeleteMapping("/employees")
    public void deleteEmployees(@RequestParam (value = "id") Long id) {
        employeesRepository.deleteById(id);
    }
}
