package com.example.payroll.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {

    private Long employee_id;

    private String first_name;
    private String last_name;
    private String department;
    private String positions;
    private String email;

    private Boolean employee_status;
    private Boolean pf_status;
}
