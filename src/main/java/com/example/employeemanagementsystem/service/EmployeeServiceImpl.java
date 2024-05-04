// EmployeeServiceImpl.java
package com.example.employeemanagementsystem.service;

import com.example.employeemanagementsystem.model.Employee;
import com.example.employeemanagementsystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        return optionalEmployee.orElse(null);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Employee existingEmployee = getEmployeeById(id);

        if (existingEmployee != null) {
            existingEmployee.setName(updatedEmployee.getName());
            existingEmployee.setAddress(updatedEmployee.getAddress());
            existingEmployee.setPhoneNumber(updatedEmployee.getPhoneNumber());
            return employeeRepository.save(existingEmployee);
        } else {
            return null;
        }
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> getEmployeeByName(String name) {
        return employeeRepository.findByName(name);
    }
}
