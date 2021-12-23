package com.luxoft.company.entity;

public class Department {
    private Integer id;
    private String title;

    public Department(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Department(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
