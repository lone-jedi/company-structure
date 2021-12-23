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

    public void write(List<Department> departments) throws IOException {
        StringBuilder stringBuilder = new StringBuilder("Колличество работников в каждом департаменте:\n");

        for (Department department : departments) {
            String line = String.format("%20s -> %d\n", department.getTitle(), department.getEmployeesCount());
            stringBuilder.append(line);
        }

        outputStream.write(stringBuilder.toString().getBytes());
    }
}
