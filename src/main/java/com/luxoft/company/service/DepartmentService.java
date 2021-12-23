package com.luxoft.company.service;

import com.luxoft.company.dao.DepartmentDao;
import com.luxoft.company.entity.Department;

import java.util.List;
import java.util.Map;

public class DepartmentService {
    private final DepartmentDao departmentDao;

    public DepartmentService(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public List<Department> getAll() {
        return departmentDao.getAll();
    }
}
