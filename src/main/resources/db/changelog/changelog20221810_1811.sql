-- liquibase formatted sql

-- changeset nika.avalishvili:1
CREATE TABLE employee (id SERIAL PRIMARY KEY,
                        personal_id VARCHAR(255),
                        first_name VARCHAR(255),
                        last_name VARCHAR(255),
                        department VARCHAR(255),
                        positions VARCHAR(255),
                        email VARCHAR(255),
                        is_active Boolean,
                        is_pensions_payer Boolean)
