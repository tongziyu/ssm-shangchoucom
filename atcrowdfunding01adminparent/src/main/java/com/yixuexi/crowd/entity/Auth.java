package com.yixuexi.crowd.entity;

import java.util.Objects;

public class Auth {
    private Integer id;

    private String name;

    private String title;

    private Integer categoryId;

    public Auth() {
    }

    public Auth(Integer id, String name, String title, Integer categoryId) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.categoryId = categoryId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auth auth = (Auth) o;
        return Objects.equals(id, auth.id) &&
                Objects.equals(name, auth.name) &&
                Objects.equals(title, auth.title) &&
                Objects.equals(categoryId, auth.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, title, categoryId);
    }

    @Override
    public String toString() {
        return "Auth{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }
}