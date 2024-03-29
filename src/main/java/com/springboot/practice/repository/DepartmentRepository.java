package com.springboot.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.practice.entity.Department;
import java.util.List;


public interface DepartmentRepository extends JpaRepository<Department, Long>{
    public List<Department> findByDepartmentName(String departmentName);
    public List<Department> findByDepartmentNameIgnoreCase(String departmentName);
}
