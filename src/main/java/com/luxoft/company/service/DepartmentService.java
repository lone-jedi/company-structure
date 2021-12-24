package com.luxoft.company.service;

import com.luxoft.company.dao.DepartmentDao;
import com.luxoft.company.entity.Department;

import java.sql.SQLException;
import java.util.List;

public class DepartmentService {
    private final DepartmentDao departmentDao;

    public DepartmentService(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public List<Department> getAll() {
        return departmentDao.getAll();
    }

    public int add(Department department, List<Integer> employeeIds) throws SQLException {
        if(employeeIds.isEmpty()) {
            throw new IllegalStateException("Employee ids is empty. Department require at least one employee.");
        }
        return departmentDao.add(department, employeeIds);
    }
}
