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

    private String firstName;
    private String lastName;
    private String department;
    private String positions;
    private String email;

    private Boolean isActive;
    private Boolean isPensionsPayer;

    public Employee(Employee.EmployeeBuilder employeeBuilder) {
        this.id = employeeBuilder.id;
        this.firstName = employeeBuilder.firstName;
        this.lastName = employeeBuilder.lastName;
        this.department = employeeBuilder.department;
        this.positions = employeeBuilder.positions;
        this.email = employeeBuilder.email;
        this.isActive = employeeBuilder.isActive;
        this.isPensionsPayer = employeeBuilder.isPensionsPayer;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public static class EmployeeBuilder{
        private Long id;
        private String firstName;
        private String lastName;
        private String department;
        private String positions;
        private String email;

        private Boolean isActive;
        private Boolean isPensionsPayer;

        public EmployeeBuilder id(Long id){
            this.id = id;
            return this;
        }
        public EmployeeBuilder first_name(String firstName){
            this.firstName = firstName;
            return this;
        }
        public EmployeeBuilder last_name(String lastName){
            this.lastName = lastName;
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

        public EmployeeBuilder is_active(boolean isActive){
            this.isActive = isActive;
            return this;
        }

        public EmployeeBuilder is_pensions_payer(boolean isPensionsPayer){
            this.isPensionsPayer = isPensionsPayer;
            return this;
        }

        public Employee buildEmployee(){
            return new Employee(this);
        }
    }
}

