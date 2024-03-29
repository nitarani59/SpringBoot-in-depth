package com.springboot.practice.service;

import com.springboot.practice.entity.Department;
import com.springboot.practice.error.DepartmentNotFoundException;

import java.util.List;

public interface DepartmentService {

    Department createDepartment(Department department);

     List<Department> findAllDepartment();

    List<Department> findAllByDepartmentName(String departmentName);

    Department findAllByDepartmentId(Long departmentId) throws DepartmentNotFoundException;
}
