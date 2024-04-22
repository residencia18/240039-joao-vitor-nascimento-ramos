CREATE TABLE suppliers (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    CPF VARCHAR(14) UNIQUE,
    CNPJ VARCHAR(18) UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone_number1 VARCHAR(20) NOT NULL,
    phone_number2 VARCHAR(20),
    activated BOOLEAN NOT NULL,
    public_place VARCHAR(255) NOT NULL,
    neighborhood VARCHAR(255) NOT NULL,
    cep VARCHAR(8) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(2) NOT NULL,
    complement VARCHAR(255),
    number VARCHAR(10)
);