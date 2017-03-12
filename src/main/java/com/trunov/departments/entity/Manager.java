package com.trunov.departments.entity;

import javax.persistence.*;

/**
 * Created by misha on 12.03.17.
 */
@javax.persistence.Entity
@Table(name = "managers", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id"),
        @UniqueConstraint(columnNames = "name"),
        @UniqueConstraint(columnNames = "lastName"),
        @UniqueConstraint(columnNames = "age"),
        @UniqueConstraint(columnNames = "type"),
        @UniqueConstraint(columnNames = "methodology"),
        @UniqueConstraint(columnNames = "department_id")})
public class Manager extends Employee {

    @Column(name = "methodology", nullable = false, length = 25)
    private String methodology;

    public Manager(String name, String lastName, int age, String type, String methodology, Department department){
        super(name, lastName, age, type, department);
        this.methodology = methodology;
    }

    public Manager(){

    }

    public String getMethodology() {
        return methodology;
    }

    public void setMethodology(String methodology) {
        this.methodology = methodology;
    }
}
