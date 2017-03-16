package com.trunov.departments.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by misha on 12.03.17.
 */
@javax.persistence.Entity
@Table(name = "departments")
public class Department extends Entity {
    private static int UNIQUE_DEPARTMENT_ID = 0;

    @OneToMany(mappedBy = "department", cascade= CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<Developer> developers = new HashSet<Developer>();

    @OneToMany(mappedBy = "department", cascade= CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<Manager> managers = new HashSet<Manager>();

    public Department(String name){
        super(name);
        this.id = UNIQUE_DEPARTMENT_ID++;
    }

    public Department(){

    }

    public Department(long id){
        this.id = id;
    }

    @Override
    public String toString(){
        return "id: " + id +
                ", Name: " + name + ".";
    }
}
