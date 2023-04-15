package com.example.springbackend.services;

import com.example.springbackend.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee saveEmployee (Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    Employee updateEmployee(Employee employee, Long id);
    void deleteEmployee(Long id);
}
