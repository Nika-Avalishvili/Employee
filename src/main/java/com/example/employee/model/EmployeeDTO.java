package com.example.employee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {

    private Long id;

    private String first_name;
    private String last_name;
    private String department;
    private String positions;
    private String email;

    private Boolean is_active;
    private Boolean is_pensions_payer;
}
