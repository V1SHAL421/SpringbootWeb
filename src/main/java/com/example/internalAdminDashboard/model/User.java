package com.example.internalAdminDashboard.model;

import jakarta.persistence.*;

@Entity
@Table(name = "profiles")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // We use the wrapper class because the ID may not be set before persisting
    private String name;
    private Integer age; // We use the wrapper class because primitive types have default values - not null

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
