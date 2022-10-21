package com.example.employee.controller;

import com.example.employee.model.EmployeeDTO;
import com.example.employee.service.EmployeeService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllEmployees() throws Exception {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFirst_name("Nika");
        employeeService.createAndUpdateEmployee(employeeDTO);
        List<EmployeeDTO> expectedEmployeeDTOList = List.of(employeeDTO);

        String responseAsAString = mockMvc.perform(MockMvcRequestBuilders.get("/employee"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<EmployeeDTO> actualEmployeeDTOList = objectMapper.readValue(responseAsAString, new TypeReference<>() {
        });

        Assertions.assertEquals(1, expectedEmployeeDTOList.size());
        Assertions.assertEquals(1, actualEmployeeDTOList.size());

        EmployeeDTO expectedEmployeeDto = expectedEmployeeDTOList.stream().findFirst().orElseThrow();
        EmployeeDTO actualEmployeeDto = actualEmployeeDTOList.stream().findFirst().orElseThrow();

        Assertions.assertEquals(expectedEmployeeDto.getFirst_name(), actualEmployeeDto.getFirst_name());
        Assertions.assertEquals(expectedEmployeeDto.getLast_name(), actualEmployeeDto.getLast_name());
    }

}