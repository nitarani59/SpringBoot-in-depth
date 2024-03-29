package com.springboot.practice.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.springboot.practice.entity.Department;

@DataJpaTest
public class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private Department department;

    @BeforeEach
    void setup() {
        department = Department.builder()
                                .id(5L)
                                .departmentName("department")
                                .build();
        testEntityManager.merge(department);
    }

    @Test
    void testFindByDepartmentName() {
        Assertions.assertEquals("department", departmentRepository.findByDepartmentName("department").get(0).getDepartmentName());
    }

    @Test
    void testFindByDepartmentNameIgnoreCase() {
        Assertions.assertEquals("department",
        departmentRepository.findByDepartmentName("department").get(0).getDepartmentName());
    }

    @Test
    void testFindById() {
        Assertions.assertEquals("department", departmentRepository.findById(5L).get().getDepartmentName());
    }

    @Test
    void testFindAll() {
        Assertions.assertEquals(1, departmentRepository.findAll().size());
    }

    @Test
    void testSave() {
        Assertions.assertEquals("department",departmentRepository.save(department).getDepartmentName());
    }
}
