package com.pojo;

public class Employee {
    private String firstName;
    private String lastName;
    private Integer age;
    private String address;
    private double salary;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                '}';
    }

    public boolean equals(Object o) {
        if(!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        if(employee.getFirstName().equals(this.getFirstName()) && this.getLastName().equals(employee.getLastName())) return true;
        return false;
    }
    @Override
    public int hashCode() {
        int hash = 5;
        hash = hash + (this.firstName != null ? this.firstName.hashCode() : 0);
        return hash;
    }


}
