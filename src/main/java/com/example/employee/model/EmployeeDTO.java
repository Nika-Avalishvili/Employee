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

    public EmployeeDTO(EmployeeDTOBuilder employeeDTOBuilder) {
        this.id = employeeDTOBuilder.id;
        this.first_name = employeeDTOBuilder.first_name;
        this.last_name = employeeDTOBuilder.last_name;
        this.department = employeeDTOBuilder.department;
        this.positions = employeeDTOBuilder.positions;
        this.email = employeeDTOBuilder.email;
        this.is_active = employeeDTOBuilder.is_active;
        this.is_pensions_payer = employeeDTOBuilder.is_pensions_payer;
    }


    @AllArgsConstructor
    @NoArgsConstructor
    public static class EmployeeDTOBuilder {
        private Long id;
        private String first_name;
        private String last_name;
        private String department;
        private String positions;
        private String email;

        private Boolean is_active;
        private Boolean is_pensions_payer;

        public EmployeeDTOBuilder id(Long id){
            this.id = id;
            return this;
        }

        public EmployeeDTOBuilder first_name(String first_name){
            this.first_name = first_name;
            return this;
        }

        public EmployeeDTOBuilder last_name(String last_name){
            this.last_name = last_name;
            return this;
        }

        public EmployeeDTOBuilder department(String department){
            this.department = department;
            return this;
        }

        public EmployeeDTOBuilder positions(String positions){
            this.positions = positions;
            return this;
        }

        public EmployeeDTOBuilder email(String email){
            this.email = email;
            return this;
        }

        public EmployeeDTOBuilder is_active(boolean is_active){
            this.is_active = is_active;
            return this;
        }

        public EmployeeDTOBuilder is_pensions_payer(boolean is_pensions_payer){
            this.is_pensions_payer = is_pensions_payer;
            return this;
        }

        public EmployeeDTO buildEmployeeDTO() {
            return new EmployeeDTO(this);
        }
    }
}
