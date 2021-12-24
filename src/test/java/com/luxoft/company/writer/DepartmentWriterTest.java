package com.luxoft.company.writer;

import com.luxoft.company.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentWriterTest {
    private DepartmentWriter departmentWriter;

    @BeforeEach
    public void before() {
        departmentWriter = new DepartmentWriter(System.out);
    }

    @Test
    public void getCountOfEmployeesByDepartment() {
        List<Department> departments = new ArrayList<>();
        departments.add(Department.builder().title("Test").employeesCount(12).build());
        departments.add(Department.builder().title("Тест").employeesCount(-2).build());
        departments.add(Department.builder().title("").employeesCount(0).build());

        String expected = "                Test -> 12\n                Тест -> -2\n                     -> 0\n";
        String actual = departmentWriter.getDepartmentsByEmployeeCount(departments);
        assertEquals(expected, actual);
    }

    @Test
    public void getCountOfEmployeesByDepartmentWhenListIsEmpty() {
        List<Department> departments = new ArrayList<>();

        String actual = departmentWriter.getDepartmentsByEmployeeCount(departments);
        assertEquals("", actual);
    }

    @Test
    public void getDepartmentWhenItInitializedByDefault() {
        Department department = Department.builder().build();

        assertThrows(IllegalStateException.class, () -> {
            departmentWriter.getDepartmentByEmployeeCount(department);
        });

        try {
            departmentWriter.getDepartmentByEmployeeCount(department);
        } catch(IllegalStateException e) {
            assertEquals("Department title must be initialized", e.getMessage());
        }
    }

    @Test
    public void getDepartmentWhenItsNull() {
        assertThrows(IllegalStateException.class, () -> {
            departmentWriter.getDepartmentByEmployeeCount(null);
        });

        try {
            departmentWriter.getDepartmentByEmployeeCount(null);
        } catch(IllegalStateException e) {
            assertEquals("Department is null. Department must be initialized before printing", e.getMessage());
        }
    }
}