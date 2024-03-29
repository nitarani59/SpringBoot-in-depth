package com.springboot.practice.controller;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.springboot.practice.entity.Department;
import com.springboot.practice.error.DepartmentNotFoundException;
import com.springboot.practice.service.DepartmentService;

@WebMvcTest(DepartmentController.class)
public class DepartmentControllerTest {

    @MockBean
    private DepartmentService departmentService;

    @Autowired
    private MockMvc mockMvc;

    private Department department;

    @BeforeEach
    void setup() throws DepartmentNotFoundException {
                department = Department.builder()
                                .id(1L)
                                .departmentName("departmentName1")
                                .build();
        Mockito.when(departmentService.createDepartment(Mockito.any())).thenReturn(department);
        Mockito.when(departmentService.findAllByDepartmentId(1L)).thenReturn(department);
        Mockito.when(departmentService.findAllByDepartmentId(100L)).thenThrow(DepartmentNotFoundException.class);
        Mockito.when(departmentService.findAllByDepartmentName("departmentName1")).thenReturn(List.of(department));
        Mockito.when(departmentService.findAllDepartment()).thenReturn(List.of(department));
    }

    @Test
    void testCreateDepartment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
        .post("/department")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"departmentName\": \"CSE\"}"))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetDepartment() throws Exception {        
        mockMvc.perform(MockMvcRequestBuilders
        .get("/department"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].departmentName").value(department.getDepartmentName()));
    }

    @Test
    void testGetDepartmentById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/department/1"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.departmentName").value(department.getDepartmentName()));
    }

    @Test
    void testGetDepartmentByName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/department/name/departmentName1"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].departmentName").value(department.getDepartmentName()));
    }

    @Test
    void testGetDepartmentByIdThrowsExceptionForNonExistentId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/department/100"))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
