package com.trunov.departments.entity;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * Created by misha on 12.03.17.
 */
@MappedSuperclass
public class Employee extends Entity {
    protected static int UNIQUE_EMPLOYEE_ID = 0;

    @Column(name = "age", nullable = false, length = 10)
    private int age;

    @Column(name = "lastName", nullable = false, length = 25)
    private String lastName;

    @Column(name = "type", nullable = false, length = 25)
    private String type;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    Employee(String name, String lastName, int age, String type, Department department) {
        super(name);
        this.lastName = lastName;
        this.age = age;
        this.type = type;
        this.department = department;
        this.id = UNIQUE_EMPLOYEE_ID++;
    }

    public Employee() {

    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
