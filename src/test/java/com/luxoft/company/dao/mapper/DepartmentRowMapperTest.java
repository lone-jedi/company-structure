package com.luxoft.company.dao.mapper;

import com.luxoft.company.entity.Department;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DepartmentRowMapperTest {
    @Test
    public void testMapRow() throws SQLException {
        DepartmentRowMapper departmentRowMapper = new DepartmentRowMapper();

        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("title")).thenReturn("Development");
        when(mockResultSet.getInt("employees_count")).thenReturn(12);

        Department actual = departmentRowMapper.mapRow(mockResultSet);

        assertEquals(1, actual.getId());
        assertEquals("Development", actual.getTitle());
        assertEquals(12, actual.getEmployeesCount());
    }
}