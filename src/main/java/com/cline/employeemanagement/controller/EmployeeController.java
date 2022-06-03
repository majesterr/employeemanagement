package com.cline.employeemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cline.employeemanagement.entity.Employee;
import com.cline.employeemanagement.repository.EmployeeRepository;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    
    @Autowired
    private EmployeeRepository employeeRepository;

    // List all Employees
    @GetMapping("/")
    public List<Employee> showEmployees()
    {
        return employeeRepository.findAll();
    }

    // Create a new Employee
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee addNewEmployee (@RequestBody Employee employee)
    {
        return employeeRepository.save(employee);
    }

    // Find an Employee by id
    @GetMapping("/{id}")
    public List<Employee> findById(@PathVariable(value = "id") int id)
    {
        return employeeRepository.findById(id);
    }

    // Delete an Employee by id
    @DeleteMapping("/delete/{id}")
    public Employee deleteEmployee(@PathVariable(value = "id") int id)
    {
        if(employeeRepository.existsById(id))
        {
            List<Employee> e = employeeRepository.findById(id);
            employeeRepository.deleteById(id);

            return e.get(0);
        }
        else
        {
            return null;
        }
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable int id)
    {
        if(employeeRepository.existsById(id))
        {
            List<Employee> e = employeeRepository.findById(id);

            employee.setId(id);
            employeeRepository.save(employee);
            
            return e.get(0);
        }

        return null;
    }

}
