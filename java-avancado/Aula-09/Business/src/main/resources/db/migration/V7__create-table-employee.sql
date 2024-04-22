CREATE TABLE employees (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    role_id INT,
    email VARCHAR(255) UNIQUE,
    cpf VARCHAR(14) UNIQUE,
    birthday DATE,
    phone_number VARCHAR(20),
    public_place VARCHAR(255) NOT NULL,
    neighborhood VARCHAR(255) NOT NULL,
    cep VARCHAR(8) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(2) NOT NULL,
    complement VARCHAR(255),
    number VARCHAR(10),
    activated BOOLEAN,
    FOREIGN KEY (role_id) REFERENCES roles(id)
);