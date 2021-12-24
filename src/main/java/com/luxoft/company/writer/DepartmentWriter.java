package com.luxoft.company.writer;

import com.luxoft.company.entity.Department;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class DepartmentWriter {
    private final OutputStream outputStream;

    public DepartmentWriter(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void writeByEmployeesCount(List<Department> departments) throws IOException {
        String departmentsWithEmployeeCount = getDepartmentsByEmployeeCount(departments);
        outputStream.write("Колличество работников в каждом департаменте:\n".getBytes());
        outputStream.write(departmentsWithEmployeeCount.getBytes());
        outputStream.flush();
    }

    // package-private for testing
    String getDepartmentsByEmployeeCount(List<Department> departments) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Department department : departments) {
            String line = getDepartmentByEmployeeCount(department);
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }

    String getDepartmentByEmployeeCount(Department department) {
        if(department == null) {
            throw new IllegalStateException("Department is null. Department must be initialized before printing");
        }

        if(department.getTitle() == null) {
            throw new IllegalStateException("Department title must be initialized");
        }

        return String.format("%20s -> %d\n", department.getTitle(), department.getEmployeesCount());
    }
}
