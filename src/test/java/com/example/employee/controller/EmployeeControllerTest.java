package com.example.employee.controller;

import com.example.employee.model.EmployeeDTO;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.service.EmployeeService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

import static org.assertj.core.api.Assertions.assertThat;
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

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void cleanUp() {
        employeeRepository.deleteAll();
    }

    @Test
    void getAllEmployees() throws Exception {
        EmployeeDTO employeeDTO = new EmployeeDTO.EmployeeDTOBuilder()
                .firstName("Nika")
                .lastName("Avalishvili")
                .department("Business development")
                .positions("Business builder")
                .email("avalishvili.nick@gmail.com")
                .isActive(true)
                .isPensionsPayer(true)
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

        assertThat(expectedEmployeeDto)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(actualEmployeeDto);
    }


    @Test
    void getEmployeeById() throws Exception {
        EmployeeDTO employeeDTO = new EmployeeDTO.EmployeeDTOBuilder()
                .firstName("Nika")
                .lastName("Avalishvili")
                .department("Business development")
                .positions("Business builder")
                .email("avalishvili.nick@gmail.com")
                .isActive(true)
                .isPensionsPayer(true)
                .buildEmployeeDTO();
        Long id = employeeService.createAndUpdateEmployee(employeeDTO).getId();

        String responseAsAString = mockMvc.perform(MockMvcRequestBuilders.get("/employee/{id}", id))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        EmployeeDTO actualEmployeeDTO = objectMapper.readValue(responseAsAString, new TypeReference<>() {
        });

        assertThat(actualEmployeeDTO)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(employeeDTO);
    }

    @Test
    void addOrUpdateEmployee() throws Exception {
        EmployeeDTO employeeDTO = new EmployeeDTO.EmployeeDTOBuilder()
                .firstName("Harry")
                .lastName("Potter")
                .department("Ministry of Magic")
                .positions("Clerk")
                .email("Harry.potter@ministryofmagic.com")
                .isActive(true)
                .isPensionsPayer(false)
                .buildEmployeeDTO();
        employeeService.createAndUpdateEmployee(employeeDTO);

        String requestJson = objectMapper.writeValueAsString(employeeDTO);

        String responseAsAString = mockMvc.perform(MockMvcRequestBuilders.post("/employee")
                        .contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        EmployeeDTO actualEmployeeDTO = objectMapper.readValue(responseAsAString, new TypeReference<>() {
        });

        assertThat(actualEmployeeDTO)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(employeeDTO);
    }

    @Test
    void deleteEmployee() throws Exception {
        EmployeeDTO employeeDTO1 = new EmployeeDTO.EmployeeDTOBuilder()
                .firstName("Harry")
                .lastName("Potter")
                .department("Ministry of Magic")
                .positions("Clerk")
                .email("Harry.potter@ministryofmagic.com")
                .isActive(true)
                .isPensionsPayer(false)
                .buildEmployeeDTO();
        EmployeeDTO employeeDTO2 = new EmployeeDTO.EmployeeDTOBuilder()
                .firstName("Albus")
                .lastName("Dumbledor")
                .department("Hogwarts")
                .positions("Director")
                .email("Albus.dumbledore@hogwarts.com")
                .isActive(true)
                .isPensionsPayer(true)
                .buildEmployeeDTO();
        Long firstId = employeeService.createAndUpdateEmployee(employeeDTO1).getId();
        employeeService.createAndUpdateEmployee(employeeDTO2);

        String firstResponseAsAString = mockMvc.perform(MockMvcRequestBuilders.delete("/employee?id={id}", firstId))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String lastResponseAsAString = mockMvc.perform(MockMvcRequestBuilders.get("/employee"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        List<EmployeeDTO> actualEmployeeDTOList = objectMapper.readValue(lastResponseAsAString, new TypeReference<>() {
        });

        assertThat(actualEmployeeDTOList)
                .hasSize(1)
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("id")
                .doesNotContain(employeeDTO1);

        EmployeeDTO actualEmployeeDTO = actualEmployeeDTOList.stream().findFirst().orElseThrow();
        assertThat(actualEmployeeDTO).usingRecursiveComparison().ignoringFields("id").isEqualTo(employeeDTO2);

    }
}