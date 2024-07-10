package com.example.internalAdminDashboard.dto;

public class UserDTO {
    private Long id; // We use the wrapper class because the ID may not be set before persisting

    private String name;

    private Integer age;

    public UserDTO() {}

    public UserDTO(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
