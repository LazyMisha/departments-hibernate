package com.trunov.departments.entity;

import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by misha on 12.03.17.
 */
@javax.persistence.Entity
@Table(name = "departments", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id"),
        @UniqueConstraint(columnNames = "name")})
public class Department extends Entity {
    private static int UNIQUE_DEPARTMENT_ID = 0;

    @OneToMany(mappedBy = "department")
    private Set<Developer> developers = new HashSet<Developer>();

    @OneToMany(mappedBy = "department")
    private Set<Manager> managers = new HashSet<Manager>();

    public Department(String name){
        super(name);
        this.id = UNIQUE_DEPARTMENT_ID++;
    }

    public Department(){

    }
}
