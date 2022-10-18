package com.example.payroll.Repositories;

import com.example.payroll.Models.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeesRepository extends JpaRepository<Employees, Long> {
}
