package org.example.unittestingusingmockito.repository;

import org.assertj.core.api.Assertions;
import org.example.unittestingusingmockito.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;
    @BeforeEach
    public  void setUp(){
        employee=Employee.builder()
                .firstName("Rabindra")
                .lastName("Adhikari")
                .email("Raj242adk@gmail.com")
                .build();
    }

    @DisplayName("Junit test for save Employee Operation")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployeed(){
        Employee employee1=Employee.builder()
                .firstName("Ramesh")
                .lastName("Ramesh")
                .email("ramesh@gmail,com")
                .build();

        Employee savedEmployee=employeeRepository.save(employee1);
        Assertions.assertThat(savedEmployee).isNotNull();
        Assertions.assertThat(savedEmployee.getId()).isGreaterThan(0);

        verify(employeeRepository,times(1)).save(employee1);
    }

}