package com.comparator;

import com.pojo.Employee;

import java.util.Comparator;

public class EmployeeSalaryComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee emp1, Employee emp2) {
        return (int) (emp2.getSalary() - emp1.getSalary());
    }
}
