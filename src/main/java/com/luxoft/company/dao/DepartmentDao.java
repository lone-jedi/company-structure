package com.luxoft.company.dao;

import com.luxoft.company.dao.mapper.DepartmentRowMapper;
import com.luxoft.company.entity.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDao {
    private static final DepartmentRowMapper DEPARTMENT_ROW_MAPPER = new DepartmentRowMapper();
    private static final String GET_EMPLOYEES_COUNT_SQL = """
            SELECT department.id, department.title, COUNT(employee_department.employee_id) AS employees_count
            FROM department
            JOIN employee_department ON employee_department.department_id = department.id
            GROUP BY department.id
            ORDER BY employees_count DESC;
            """;
    private static final String INSERT_SQL = """
            INSERT INTO department(title) VALUES (?);
            """;
    private static final String INSERT_EMPLOYEE_SQL = """
            INSERT INTO employee_department(employee_id, department_id) VALUES (?, ?);
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
            throw new RuntimeException("Error with getting count of employees by departments", e);
        }
    }

    public int add(Department department, List<Integer> employeeIds) throws SQLException {
        try {
            connection.setAutoCommit(false);

            int departmentId = add(department);

            for (int employeeId : employeeIds) {
                addEmployeeToDepartment(employeeId, departmentId);
            }

            connection.commit();
            return departmentId;
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException("Error with department adding", e);
        } finally {
            connection.setAutoCommit(true);
        }
    }

    private int add(Department department) throws SQLException {
        try (PreparedStatement departmentPreparedStatement = connection.prepareStatement(INSERT_SQL,
                Statement.RETURN_GENERATED_KEYS)) {
            departmentPreparedStatement.setString(1, department.getTitle());

            int affectedRows = departmentPreparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating department failed, no rows affected.");
            }

            try (ResultSet generatedKeys = departmentPreparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
                else {
                    throw new SQLException("Creating department failed, no ID obtained.");
                }
            }
        }
    }

    private int addEmployeeToDepartment(int employeeId, int departmentId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE_SQL,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, employeeId);
            preparedStatement.setInt(2, departmentId);
            return preparedStatement.executeUpdate();
        }
    }
}
