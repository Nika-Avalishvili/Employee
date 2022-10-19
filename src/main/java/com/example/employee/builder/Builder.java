package com.example.employee.builder;

import com.example.employee.model.Employee;
import com.example.employee.model.EmployeeDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Builder {
    @Getter
    private Long id;

    @Getter
    private String first_name;
    @Getter
    private String last_name;
    @Getter
    private String department;
    @Getter
    private String positions;
    @Getter
    private String email;

    @Getter
    private Boolean is_active;
    @Getter
    private Boolean is_pensions_payer;


    public Builder setId(Long id){
        this.id = id;
        return this;
    }

    public Builder setFirst_name(String first_name){
        this.first_name = first_name;
        return this;
    }

    public Builder setLast_name(String last_name){
        this.last_name = last_name;
        return this;
    }

    public Builder setDepartment(String department) {
        this.department = department;
        return this;
    }

    public Builder setPositions(String positions) {
        this.positions = positions;
        return this;
    }

    public Builder setEmail(String email) {
        this.email = email;
        return this;
    }

    public Builder setIs_active(Boolean is_active) {
        this.is_active = is_active;
        return this;
    }

    public Builder setIs_pensions_payer(Boolean is_pensions_payer) {
        this.is_pensions_payer = is_pensions_payer;
        return this;
    }

    public Employee buildEmployee(){
        return new Employee(this);
    }

    public EmployeeDTO buildEmployeeDTO(){
        return new EmployeeDTO(this);
    }
}
