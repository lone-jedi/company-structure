package com.luxoft.company.dao.mapper;

import com.luxoft.company.entity.Department;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentRowMapper {
    public Department mapRow(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        int employeesCount = resultSet.getInt("employees_count");

        return Department.builder()
                .id(id)
                .title(title)
                .employeesCount(employeesCount)
                .build();
    }
}
