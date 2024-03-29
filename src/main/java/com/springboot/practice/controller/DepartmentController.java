package com.springboot.practice.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.practice.entity.Department;
import com.springboot.practice.error.DepartmentNotFoundException;
import com.springboot.practice.service.DepartmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class DepartmentController {
   
    @Autowired
    DepartmentService departmentService;
    @PostMapping("/department")
    public Department createDepartment(@RequestBody Department department) {
        return departmentService.createDepartment(department);
    }

    @GetMapping("/department")
    public List<Department> getDepartment() {
        return departmentService.findAllDepartment();
    }

    @GetMapping("/department/name/{name}")
    public List<Department> getDepartmentByName(@PathVariable(value = "name") String departmentName) {
        return departmentService.findAllByDepartmentName(departmentName);
    }

    @GetMapping("/department/{id}")
    public Department getDepartmentById(@PathVariable(value = "id") Long departmentId) throws DepartmentNotFoundException {
        return departmentService.findAllByDepartmentId(departmentId);
    }
}
