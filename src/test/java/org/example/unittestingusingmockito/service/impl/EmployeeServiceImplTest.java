package org.example.unittestingusingmockito.service.impl;

import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.example.unittestingusingmockito.entity.Employee;
import org.example.unittestingusingmockito.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {
    private Employee employee;
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    public void setUp() {
        employee = Employee.builder()
                .id(1L)
                .firstName("Raj")
                .lastName("Adhikari")
                .email("raj2242adk@gmail.com")
                .build();
    }

    @DisplayName("Test for the Junit save Employee methods")
    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployee() {
        //given pre condition or setup
        given(employeeRepository.findByEmail(employee.getEmail()))
                .willReturn(Optional.empty());
        given(employeeRepository.save(employee)).willReturn(employee);
        System.out.println(employeeRepository);
        System.out.println(employeeService);
        //when acction or behavior that we are going to test

        Employee savedEmployee = employeeService.saveEmployee(employee);
        //then verify the output
        Assertions.assertThat(savedEmployee).isNotNull();
        System.out.println(savedEmployee.toString());
    }

    @DisplayName("Junit test for saveEmployee which throws the Exception")
    @Test
    public void givenExistingEmail_whenSaveEmployee_thenThrowsException() {

        //given
        given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.of(employee));
        //when
        org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> {
            employeeService.saveEmployee(employee);
        });
        //then
        verify(employeeRepository, never()).save(any(Employee.class));
    }

    @Test
    @DisplayName("Junit test for getAllEmployees method")
    public void givenEmployeeList_whenGetAllEmployees_thenReturnEmployee() {
        //given pre condition setup
        Employee employee1 = Employee.builder()
                .id(1L)
                .firstName("Raj")
                .lastName("Adhikari")
                .email("raj2242adk@gmail.com")
                .build();
        given(employeeRepository.findAll()).willReturn(List.of(employee, employee1));
        //when -action or the behavior that we are going to test
        List<Employee> employeeList = employeeService.getAllEmployees();
        Assertions.assertThat(employeeList).isNotNull();
        Assertions.assertThat(employeeList.size()).isEqualTo(2);
    }

    @DisplayName("Junit test for getAllEmployee")
    @Test
    public void givenEmptyEmployeeList_whenGetAllEmployees_thenReturnEmptyEmployeeList() {
        given(employeeRepository.findAll()).willReturn(Collections.emptyList());
        //when
        List<Employee> employeeList = employeeService.getAllEmployees();
        //then
        Assertions.assertThat(employeeList).isEmpty();
        Assertions.assertThat(employeeList.size()).isEqualTo(0);

    }

    @DisplayName("Junit test for getEmployeeById")
    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenRetunOptionalOfEmployee() {
        //given
        given(employeeRepository.findById(1L)).willReturn(Optional.of(employee));
        //when
        Optional<Employee> employee1 = employeeService.getEmployeeById(employee.getId());
        //then
        Assertions.assertThat(employee1).isNotNull();
    }
    @DisplayName("Junit test for getEmployeeBy Id when Null")
    @Test
    public void givenEmployeeId_whenGetEmployeeById_theReturnEmptyEmployee() {
        //given
        given(employeeRepository.findById(1L)).willReturn(Optional.empty());
        //when
        Optional<Employee> employee1 = employeeService.getEmployeeById(employee.getId());
        //then
        Assertions.assertThat(employee1).isEmpty();
    }

    @Test
    @DisplayName("Junit test for updateEmployee method")
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee(){
        given(employeeRepository.save(employee)).willReturn(employee);
        employee.setEmail("raj242adk@gmail.com");
        employee.setFirstName("Ram");
        Employee updateEmployee=employeeService.updateEmployee(employee);
        Assertions.assertThat(updateEmployee.getEmail()).isEqualTo("raj242adk@gmail.com");
        Assertions.assertThat(updateEmployee.getFirstName()).isEqualTo("Ram");
    }


}