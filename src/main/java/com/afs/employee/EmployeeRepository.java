package com.afs.employee;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class EmployeeRepository {
    List<Employee> employees;

    public EmployeeRepository(){
        this.employees = new ArrayList<>();
        employees.add(new Employee(1, "Lily", 20, "Female", 8000));
        employees.add(new Employee(2, "A", 20, "Male", 8000));
        employees.add(new Employee(3, "B", 20, "Female", 8000));
        employees.add(new Employee(4, "C", 20, "Male", 8000));
    }
    public List<Employee> findAll() {
        return this.employees;
    }

    public Employee findById(Integer id) {
        return employees.stream().filter(employee -> Objects.equals(employee.getId(), id)).findFirst().orElseThrow(EmployeeNotFoundException::new) ;
    }

    public List<Employee> findByGender(String gender) {
        return employees.stream().filter(employee -> employee.getGender().equals(gender)).collect(Collectors.toList());
    }

    public Employee create(Employee employee) {
        Integer id = generateNextId();
        employee.setId(id);
        employees.add(employee);
        return employee;
    }

    private Integer generateNextId() {
        int lastId =  employees.stream()
                .mapToInt(Employee::getId)
                .max()
                .orElse(1);
        return lastId + 1;
    }

    public Employee update(Integer id, Employee employee) {
        Employee existingEmployee = findById(id);
        if(employee.getAge() != null){
            existingEmployee.setAge(employee.getAge());
        }
        if(employee.getSalary() != null){
            existingEmployee.setSalary(employee.getSalary());
        }
        return existingEmployee;
    }

    public void delete(Integer id) {
        Employee existingEmployee = findById(id);
        employees.remove(existingEmployee);
    }

    public List<Employee> getEmployeeByPage(Integer page, Integer pageSize) {
        return employees.stream()
            .skip((long)(page - 1) * pageSize)
            .limit(pageSize).collect(Collectors.toList());
    }
}
