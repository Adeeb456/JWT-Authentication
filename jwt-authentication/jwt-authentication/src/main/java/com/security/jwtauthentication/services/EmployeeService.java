package com.security.jwtauthentication.services;

import com.security.jwtauthentication.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    List<Employee> employeeList;
    public EmployeeService(){
        this.employeeList = new ArrayList<>();
        employeeList.add(new Employee(UUID.randomUUID().toString(), "Hrishab Sharma", "hrishabsharma09@gmail.com"));
        employeeList.add(new Employee(UUID.randomUUID().toString(), "John Doe", "john.doe@example.com"));
        employeeList.add(new Employee(UUID.randomUUID().toString(), "Alice Smith", "alice.smith@example.com"));
        employeeList.add(new Employee(UUID.randomUUID().toString(), "Bob Johnson", "bob.johnson@example.com"));
        employeeList.add(new Employee(UUID.randomUUID().toString(), "Emily Davis", "emily.davis@example.com"));
        employeeList.add(new Employee(UUID.randomUUID().toString(), "Michael Wilson", "michael.wilson@example.com"));
        employeeList.add(new Employee(UUID.randomUUID().toString(), "Sarah Brown", "sarah.brown@example.com"));
        employeeList.add(new Employee(UUID.randomUUID().toString(), "David Jones", "david.jones@example.com"));
        employeeList.add(new Employee(UUID.randomUUID().toString(), "Emma Martinez", "emma.martinez@example.com"));
        employeeList.add(new Employee(UUID.randomUUID().toString(), "James Anderson", "james.anderson@example.com"));
        employeeList.add(new Employee(UUID.randomUUID().toString(), "Olivia Garcia", "olivia.garcia@example.com"));
    }

    public List<Employee> getEmployeeList(){
        return this.employeeList;
    }
}
