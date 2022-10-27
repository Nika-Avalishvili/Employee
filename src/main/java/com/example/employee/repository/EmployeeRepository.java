package com.example.employee.repository;

import com.example.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findEmployeeByFirstNameAndLastNameAndDepartmentAndPositionsAndEmailAndIsActiveAndIsPensionsPayer(String first_name, String last_name, String department, String position, String email, Boolean is_active, Boolean is_pensions_payer);
}
