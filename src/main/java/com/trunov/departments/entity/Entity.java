package com.trunov.departments.entity;

import javax.persistence.*;

/**
 * Created by misha on 12.03.17.
 */
@MappedSuperclass
public class Entity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    protected long id;

    @Column(name = "name", nullable = false, length = 25)
    protected String name;

    public Entity() {

    }

    Entity(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
