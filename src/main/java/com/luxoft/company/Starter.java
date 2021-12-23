package com.luxoft.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Starter {
    public static void main(String[] args) {
        String DB_URL = "jdbc:postgresql://127.0.0.1:5432/company";
        String DB_USER = "postgres";
        String DB_PASSWORD = "121212";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            DepartmentDao departmentDao = new DepartmentDao(connection);
            DepartmentService departmentService = new DepartmentService(departmentDao);
            DepartmentWriter departmentWriter = new DepartmentWriter(System.out);

            Map<Department, Integer> employeesCount = departmentService.getEmployeesCount();
            departmentWriter.write(employeesCount);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
