CREATE TABLE sales (
    id SERIAL PRIMARY KEY,
    id_client BIGINT,
    id_employee BIGINT,
    gross_value NUMERIC,
    value_discount NUMERIC,
    final_value NUMERIC,
    date_register TIMESTAMP,
    FOREIGN KEY (id_client) REFERENCES clients(id),
    FOREIGN KEY (id_employee) REFERENCES employees(id)
);
