package com.springboot.practice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.practice.entity.Department;
import com.springboot.practice.error.DepartmentNotFoundException;
import com.springboot.practice.repository.DepartmentRepository;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentsServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;

    private Logger logger = LoggerFactory.getLogger(DepartmentsServiceImpl.class);
    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> findAllDepartment() {
        return departmentRepository.findAll();
    }

    @Override
    public List<Department> findAllByDepartmentName(String departmentName) {
        return departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
    }

    @Override
    public Department findAllByDepartmentId(Long departmentId) throws DepartmentNotFoundException {
        Optional<Department> department = departmentRepository.findById(departmentId);
        if (department.isPresent()) {
            return department.get();
        }
        else {
            throw new DepartmentNotFoundException("Department with department id `" + departmentId + "` doesn't exist.");
        }
    }
    
}
