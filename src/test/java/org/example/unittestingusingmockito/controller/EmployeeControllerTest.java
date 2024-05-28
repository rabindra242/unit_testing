package org.example.unittestingusingmockito.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.unittestingusingmockito.entity.Employee;
import org.example.unittestingusingmockito.service.EmployeeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeService employeeService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Junit test for controller createEmployee")
    public void givenEmployeeObject_whenCreateEmployee_thenReturnSavedEmplyee() throws Exception {
        //given
        Employee employee = Employee.builder().id(1L).email("Raj242adk@gmail.com").lastName("Adhikari").firstName("Raj").build();
        given(employeeService.saveEmployee(any(Employee.class))).willAnswer((invocation -> invocation.getArgument(0)));
        //when action that we are going to test
        ResultActions resultActions = mockMvc.perform(post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(employee)));
        //then
        resultActions.andDo(print()).andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName",
                        is(employee.getFirstName()))).andExpect(jsonPath("$.lastName", is(employee.getLastName())))
                .andExpect(jsonPath("$.email", is(employee.getEmail())));
    }

    @DisplayName("Junit test for the controller getAll")
    @Test
    public void givenListOfEmplloyee_whenGetAllEmployee_thenReturnEmployeeList() throws Exception {
        //given -precondition or setUp
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(Employee.builder().firstName("raj").lastName("Adhikari").email("raj242adk@gmail.com").build());
        employeeList.add(Employee.builder().email("ramdev@Tamang").lastName("Tamang").firstName("Ramdev").build());
        given(employeeService.getAllEmployees()).willReturn(employeeList);
        //when
        ResultActions response = mockMvc.perform(get("/api/employees"));
        //then
        response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.size()", is(employeeList.size())));
    }

    @DisplayName("Junit test for the controller getAll return empty")
    @Test
    public void givenEmptyList_whenGetAllEmployeeThenReturnEmptyEmployeeList() throws Exception {

        List list = Collections.emptyList();
        given(employeeService.getAllEmployees()).willReturn(list);
        //when
        ResultActions response = mockMvc.perform(get("/api/employees"));
        //then
        response.andDo(print())
                .andExpect(jsonPath("$", hasSize(0)));
    }
    @DisplayName("Junit test for findEmployeeById")
    @Test
    public void givenEmployeeId_whenGetEmployeeId_thenReturnEmployeeObject() throws Exception {
        //given
        long employeeId=1L;
        Employee employee1=Employee.builder()
                .firstName("Raj")
                .lastName("Adhikari")
                .email("raj242adk@gmail.com")
                .build();
        given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.of(employee1));
        // when
        ResultActions response=mockMvc.perform(get("/api/employees/{id}",employeeId));
        //then
        response
                .andDo(print())
                .andExpect(jsonPath("$.firstName",is(employee1.getFirstName())))
                .andExpect(jsonPath("$.email",is(employee1.getEmail())))
                .andExpect(jsonPath("$.lastName",is(employee1.getLastName()))
                );
    }

    @DisplayName("Junit test for invalid findByEmployeeId")
    @Test
    public void givenInvalidEmployeeId_whenGetEmployee_thenReturnEmptyEmployeeObject() throws Exception {
        long employeeId=1L;
        Employee employee1=Employee.builder()
                .firstName("Raj")
                .lastName("Adhikari")
                .email("raj242adk@gmail.com")
                .build();
        given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.of(employee1));
        ResultActions response=mockMvc.perform(get("/api/employees/{id}",employeeId));
        response.andExpect(status().isNotFound())
                .andDo(print());
    }
    @DisplayName("Junit test fot the updateEmployee")
    @Test
    public void givenUpdateEmployee_whenUpdateEmployee_thenReturnUpdatedEmployeeObject() throws Exception {
        long employeeId=1L;
        Employee employee1=Employee.builder()
                .firstName("Raj")
                .lastName("Adhikari")
                .email("raj242adk@gmail.com")
                .build();


        Employee updateEmployee=Employee.builder()
                .firstName("James")
                .lastName("Tamang")
                .email("james2002@gmail.com")
                .build();
        given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.of(employee1));
        given(employeeService.updateEmployee(any(Employee.class))).
                willAnswer((invocation -> invocation.getArgument(0)));
        ResultActions response=mockMvc.perform(put("/api/employees/{id}",employeeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateEmployee))
        );
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName",is(updateEmployee.getFirstName())))
                .andExpect(jsonPath("$.lastName",is(updateEmployee.getLastName())))
                .andExpect(jsonPath("$.email",is(updateEmployee.getEmail())));
    }
    @DisplayName("Test case when the the updated employee id is not found")
    @Test
    public void givenInvalidEmployeeId_whenUpdateEmployee_thenProvideError() throws Exception {
        long employeeId=1L;
        Employee updateEmployee=Employee.builder()
                .firstName("James")
                .lastName("Tamang")
                .email("james2002@gmail.com")
                .build();

        given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.empty());
        given(employeeService.updateEmployee(any(Employee.class)))
                .willAnswer((invocation -> invocation.getArgument(0)));

        ResultActions resultActions=mockMvc.perform(put("/api/employees/{id}",employeeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateEmployee))
        );
        resultActions.andDo(print())
                .andExpect(status().isNotFound());
    }

}