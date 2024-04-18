CREATE TABLE clients (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    birthday DATE,
    phone_number VARCHAR(20) NOT NULL,
    activated BOOLEAN NOT NULL,
    public_place VARCHAR(255) NOT NULL,
    neighborhood VARCHAR(255) NOT NULL,
    cep VARCHAR(8) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(2) NOT NULL,
    complement VARCHAR(255),
    number VARCHAR(10)
);