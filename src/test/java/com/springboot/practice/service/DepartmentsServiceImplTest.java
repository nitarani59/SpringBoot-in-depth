package com.springboot.practice.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.springboot.practice.entity.Department;
import com.springboot.practice.error.DepartmentNotFoundException;
import com.springboot.practice.repository.DepartmentRepository;
import java.util.List;

@SpringBootTest
public class DepartmentsServiceImplTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    private Department department;

    @BeforeEach
    public void setup() {
        department = Department.builder()
                                .id(1L)
                                .departmentName("departmentName1")
                                .build();
        Mockito.when(departmentRepository.save(Mockito.any())).thenReturn(department);
        Mockito.when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));

        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("departmentName1")).thenReturn(List.of(department));
        Mockito.when(departmentRepository.findAll()).thenReturn(List.of(department));
    }

    @Test
    void testCreateDepartment() {
        assertEquals("departmentName1", departmentService.createDepartment(department).getDepartmentName());
    }

    @Test
    void testFindAllByDepartmentId() throws DepartmentNotFoundException {
        assertEquals("departmentName1", departmentService.findAllByDepartmentId(1L).getDepartmentName());
    }

    @Test
    void testFindAllByDepartmentName() {
        assertEquals(1, departmentService.findAllByDepartmentName("departmentName1").get(0).getId());
        assertEquals("departmentName1", departmentService.findAllByDepartmentName("departmentName1").get(0).getDepartmentName());
    }

    @Test
    void testFindAllDepartment() {
        assertEquals(1, departmentService.findAllDepartment().get(0).getId());
        assertEquals("departmentName1", departmentService.findAllDepartment().get(0).getDepartmentName());
    }

    @Test
    void testFindAllByDepartmentIdThrowsExceptionForNonExistentId() {
        Assertions.assertThrows(DepartmentNotFoundException.class, () -> departmentService.findAllByDepartmentId(2L));
    }

    @Test
    void testFindAllByDepartmentNameForNonExistentName() {
        Assertions.assertEquals(List.of(), departmentService.findAllByDepartmentName("invalid"));}
}
