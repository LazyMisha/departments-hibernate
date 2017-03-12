package com.trunov.departments.entity;

import javax.persistence.*;

/**
 * Created by misha on 12.03.17.
 */
@javax.persistence.Entity
@Table(name = "developers", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id"),
        @UniqueConstraint(columnNames = "name"),
        @UniqueConstraint(columnNames = "lastName"),
        @UniqueConstraint(columnNames = "age"),
        @UniqueConstraint(columnNames = "type"),
        @UniqueConstraint(columnNames = "language"),
        @UniqueConstraint(columnNames = "department_id")})
public class Developer extends Employee {

    @Column(name = "language", nullable = false, length = 25)
    private String language;

    public Developer(String name, String lastName, int age, String type, String language, Department department){
        super(name, lastName, age, type, department);
        this.language = language;
    }

    public Developer(){

    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
