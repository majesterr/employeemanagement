package com.cline.employeemanagement.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cline.employeemanagement.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    
    public List<Employee> findAll();

    public List<Employee> findById(int id);

}
