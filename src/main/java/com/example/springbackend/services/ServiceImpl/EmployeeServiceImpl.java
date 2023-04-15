package com.example.springbackend.services.ServiceImpl;

import com.example.springbackend.exception.ResourceNotFoundException;
import com.example.springbackend.model.Employee;
import com.example.springbackend.repository.EmployeeRepository;
import com.example.springbackend.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);

//        if(employee.isPresent()) {
//            return employee.get();
//        } else {
//            throw new ResourceNotFoundException("Employee", "Id", id);
//        }

        return employeeRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Employee", "Id", id));
    }

    @Override
    public Employee updateEmployee(Employee employee, Long id) {
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Employee", "Id", id));

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        employeeRepository.save(existingEmployee);
        return existingEmployee;
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Employee", "id", id));

        employeeRepository.deleteById(id);
    }


}
