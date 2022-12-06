package com.afs.employee;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {
    List<Employee> employees;

    public EmployeeRepository(){
        this.employees = new ArrayList<>();
        employees.add(new Employee(1, "Lily", 20, "Female", 8000));
    }
    public List<Employee> findAll() {
        return this.employees;
    }

    public Employee findById(int id) {
        return employees.stream().filter(employee -> employee.getId() == id).findFirst().get();
    }
}
