package com.example.employee.model;

import com.example.employee.builder.Builder;
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

    public Employee(Builder builder){
        this.id = builder.getId();
        this.first_name = builder.getFirst_name();
        this.last_name = builder.getLast_name();
        this.department = builder.getDepartment();
        this.positions = builder.getPositions();
        this.is_active = builder.getIs_active();
        this.is_pensions_payer = builder.getIs_pensions_payer();
    }
}

