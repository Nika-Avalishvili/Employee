package com.example.employee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String department;
    private String positions;
    private String email;

    private Boolean isActive;
    private Boolean isPensionsPayer;

    public EmployeeDTO(EmployeeDTOBuilder employeeDTOBuilder) {
        this.id = employeeDTOBuilder.id;
        this.firstName = employeeDTOBuilder.firstName;
        this.lastName = employeeDTOBuilder.lastName;
        this.department = employeeDTOBuilder.department;
        this.positions = employeeDTOBuilder.positions;
        this.email = employeeDTOBuilder.email;
        this.isActive = employeeDTOBuilder.isActive;
        this.isPensionsPayer = employeeDTOBuilder.isPensionsPayer;
    }


    @AllArgsConstructor
    @NoArgsConstructor
    public static class EmployeeDTOBuilder {
        private Long id;
        private String firstName;
        private String lastName;
        private String department;
        private String positions;
        private String email;

        private Boolean isActive;
        private Boolean isPensionsPayer;

        public EmployeeDTOBuilder id(Long id){
            this.id = id;
            return this;
        }

        public EmployeeDTOBuilder firstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public EmployeeDTOBuilder lastName(String lastName){
            this.lastName = lastName;
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

        public EmployeeDTOBuilder isActive(boolean isActive){
            this.isActive = isActive;
            return this;
        }

        public EmployeeDTOBuilder isPensionsPayer(boolean isPensionsPayer){
            this.isPensionsPayer = isPensionsPayer;
            return this;
        }

        public EmployeeDTO buildEmployeeDTO() {
            return new EmployeeDTO(this);
        }
    }
}
