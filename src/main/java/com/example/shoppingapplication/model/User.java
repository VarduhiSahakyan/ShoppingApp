package com.example.shoppingapplication.model;

import java.util.Objects;

public class User {

    private String name;
    private String password;
    private Role role;
    private Category category;

    public User() {
    }

    public User(String name, String password, Role role, Category category) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && role == user.role && category == user.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, role, category);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", category=" + category +
                '}';
    }
}
