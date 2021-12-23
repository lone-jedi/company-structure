package com.luxoft.company.entity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Department {
    private Integer id;
    private final String title;
    private int employeesCount;
}
