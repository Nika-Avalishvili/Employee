package com.example.employee.controller;

import com.example.employee.model.EmployeeDTO;
import com.example.employee.service.EmployeeService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
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

import static org.springframework.http.MediaType.APPLICATION_JSON;
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
        EmployeeDTO employeeDTO = new EmployeeDTO.EmployeeDTOBuilder()
                .id(1L)
                .first_name("Nika")
                .last_name("Avalishvili")
                .department("Business development")
                .positions("Business builder")
                .email("avalishvili.nick@gmail.com")
                .is_active(true)
                .is_pensions_payer(true)
                .buildEmployeeDTO();
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
        Assertions.assertEquals(expectedEmployeeDto.getDepartment(), actualEmployeeDto.getDepartment());
        Assertions.assertEquals(expectedEmployeeDto.getPositions(), actualEmployeeDto.getPositions());
        Assertions.assertEquals(expectedEmployeeDto.getEmail(), actualEmployeeDto.getEmail());
        Assertions.assertEquals(expectedEmployeeDto.getIs_active(), actualEmployeeDto.getIs_active());
        Assertions.assertEquals(expectedEmployeeDto.getIs_pensions_payer(), actualEmployeeDto.getIs_pensions_payer());
    }

    @Test
    void getEmployeeById() throws Exception {
        EmployeeDTO employeeDTO = new EmployeeDTO.EmployeeDTOBuilder()
                .id(1L)
                .first_name("Nika")
                .last_name("Avalishvili")
                .department("Business development")
                .positions("Business builder")
                .email("avalishvili.nick@gmail.com")
                .is_active(true)
                .is_pensions_payer(true)
                .buildEmployeeDTO();
        employeeService.createAndUpdateEmployee(employeeDTO);

        String responseAsAString = mockMvc.perform(MockMvcRequestBuilders.get("/employee/{id}",1))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        EmployeeDTO actualEmployeeDTO = objectMapper.readValue(responseAsAString, new TypeReference<>(){});

        Assertions.assertEquals(employeeDTO, actualEmployeeDTO);
    }

    @Test
    void addOrUpdateEmployee() throws Exception {
        EmployeeDTO employeeDTO = new EmployeeDTO.EmployeeDTOBuilder()
                .id(1L)
                .first_name("Harry")
                .last_name("Potter")
                .department("Ministry of Magic")
                .positions("Clerk")
                .email("Harry.potter@ministryofmagic.com")
                .is_active(true)
                .is_pensions_payer(false)
                .buildEmployeeDTO();
        employeeService.createAndUpdateEmployee(employeeDTO);

        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(employeeDTO);

        String responseAsAString = mockMvc.perform(MockMvcRequestBuilders.post("/employee")
                        .contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        EmployeeDTO actualEmployeeDTO = objectMapper.readValue(responseAsAString, new TypeReference<>(){});

        Assertions.assertEquals(employeeDTO, actualEmployeeDTO);
    }


    @Test
    void deleteEmployee() throws Exception {
        EmployeeDTO employeeDTO1 = new EmployeeDTO.EmployeeDTOBuilder()
                .id(1L)
                .first_name("Harry")
                .last_name("Potter")
                .department("Ministry of Magic")
                .positions("Clerk")
                .email("Harry.potter@ministryofmagic.com")
                .is_active(true)
                .is_pensions_payer(false)
                .buildEmployeeDTO();
        EmployeeDTO employeeDTO2 = new EmployeeDTO.EmployeeDTOBuilder()
                .id(2L)
                .first_name("Albus")
                .last_name("Dumbledor")
                .department("Hogwarts")
                .positions("Director")
                .email("Albus.dumbledore@hogwarts.com")
                .is_active(true)
                .is_pensions_payer(true)
                .buildEmployeeDTO();
        employeeService.createAndUpdateEmployee(employeeDTO1);
        employeeService.createAndUpdateEmployee(employeeDTO2);


        String firstResponseAsAString = mockMvc.perform(MockMvcRequestBuilders.delete("/employee?id={id}",1))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String lastResponseAsAString = mockMvc.perform(MockMvcRequestBuilders.get("/employee"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        List<EmployeeDTO> actualEmployeeDTOList = objectMapper.readValue(lastResponseAsAString, new TypeReference<>() {
        });

        Assertions.assertNotEquals(employeeDTO1, actualEmployeeDTOList.stream().findFirst().get());
        Assertions.assertEquals(employeeDTO2, actualEmployeeDTOList.stream().findFirst().get());
    }
}