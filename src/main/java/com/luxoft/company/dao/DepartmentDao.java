package com.luxoft.company.dao;

import com.luxoft.company.dao.mapper.DepartmentRowMapper;
import com.luxoft.company.entity.Department;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentDao {
    private static final DepartmentRowMapper DEPARTMENT_ROW_MAPPER = new DepartmentRowMapper();
    private static final String GET_EMPLOYEES_COUNT_SQL = """
            SELECT department.id, department.title, COUNT(employee_department.employee_id) AS employees_count
            FROM department
            LEFT JOIN employee_department ON employee_department.department_id = department.id
            GROUP BY department.id
            ORDER BY employees_count DESC;
            """;

    private final Connection connection;


    public DepartmentDao(Connection connection) {
        this.connection = connection;
    }

    public List<Department> getAll() {
        try (ResultSet resultSet = connection.prepareStatement(GET_EMPLOYEES_COUNT_SQL).executeQuery()) {
            List<Department> employeesCountByDepartment = new ArrayList<>();

            while (resultSet.next()) {
                Department department = DEPARTMENT_ROW_MAPPER.mapRow(resultSet);
                employeesCountByDepartment.add(department);
            }

            return employeesCountByDepartment;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error with getting count of employees by departments", e);
        }
    }
}
