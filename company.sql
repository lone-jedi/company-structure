CREATE TABLE employee(
     id SERIAL PRIMARY KEY,
     first_name VARCHAR(255) NOT NULL,
     last_name VARCHAR(255) NOT NULL
);

CREATE TABLE department(
   id SERIAL PRIMARY KEY,
   title VARCHAR(255)
);

CREATE TABLE employee_department(
    employee_id INTEGER,
    department_id INTEGER,
    PRIMARY KEY(employee_id, department_id)
);

ALTER TABLE employee_department
ADD CONSTRAINT fk_employee_department
FOREIGN KEY(employee_id)
REFERENCES employee(id);

ALTER TABLE employee_department
ADD CONSTRAINT fk_department_employee
FOREIGN KEY(department_id)
REFERENCES department(id);