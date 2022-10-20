package com.example.employee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String first_name;
    private String last_name;
    private String department;
    private String positions;
    private String email;

    private Boolean is_active;
    private Boolean is_pensions_payer;

    public Employee(Employee.EmployeeBuilder employeeBuilder) {
        this.id = employeeBuilder.id;
        this.first_name = employeeBuilder.first_name;
        this.last_name = employeeBuilder.last_name;
        this.department = employeeBuilder.department;
        this.positions = employeeBuilder.positions;
        this.email = employeeBuilder.email;
        this.is_active = employeeBuilder.is_active;
        this.is_pensions_payer = employeeBuilder.is_pensions_payer;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public static class EmployeeBuilder{
        private Long id;
        private String first_name;
        private String last_name;
        private String department;
        private String positions;
        private String email;

        private Boolean is_active;
        private Boolean is_pensions_payer;

        public EmployeeBuilder id(Long id){
            this.id = id;
            return this;
        }
        public EmployeeBuilder first_name(String first_name){
            this.first_name = first_name;
            return this;
        }
        public EmployeeBuilder last_name(String last_name){
            this.last_name = last_name;
            return this;
        }
        public EmployeeBuilder department(String department){
            this.department = department;
            return this;
        }
        public EmployeeBuilder positions(String positions){
            this.positions = positions;
            return this;
        }
        public EmployeeBuilder email(String email){
            this.email = email;
            return this;
        }

        public EmployeeBuilder is_active(boolean is_active){
            this.is_active = is_active;
            return this;
        }

        public EmployeeBuilder is_pensions_payer(boolean is_pensions_payer){
            this.is_pensions_payer = is_pensions_payer;
            return this;
        }

        public Employee buildEmployee(){
            return new Employee(this);
        }
    }
}

