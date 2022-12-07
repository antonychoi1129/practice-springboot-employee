package com.afs.employee;

public class EmployeeNotFoundException extends RuntimeException  {
    public EmployeeNotFoundException() {
        super("Employee Not Found!");
    }

}
