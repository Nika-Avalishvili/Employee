package com.example.employee.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String department;
    private String positions;
    private String email;

    private Boolean isActive;
    private Boolean isPensionsPayer;

}
