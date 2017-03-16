package com.trunov.departments.entity;

import javax.persistence.*;

/**
 * Created by misha on 12.03.17.
 */
@javax.persistence.Entity
@Table(name = "developers")
public class Developer extends Employee {

    @Column(name = "language", nullable = false, length = 25)
    private String language;

    public Developer(String name, String lastName, int age, String type, String language, Department department){
        super(name, lastName, age, type, department);
        this.language = language;
    }

    public Developer(){

    }

    public Developer(long id){
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString(){
        return "id: " + id +
                ", Name: " + name +
                ", Last Name: " + getLastName() +
                ", Age: " + getAge() +
                ", Type: " + getType() +
                ", Language: " + language +
                ", Department: " + getDepartment().name;
    }
}
