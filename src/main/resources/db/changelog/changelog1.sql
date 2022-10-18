-- liquibase formatted sql

-- changeset nika.avalishvili:1
CREATE TABLE employees (employee_id SERIAL PRIMARY KEY,
                        first_name VARCHAR(255),
                        last_name VARCHAR(255),
                        department VARCHAR(255),
                        positions VARCHAR(255),
                        email VARCHAR(255),
                        employee_status Boolean,
                        pf_status Boolean)
