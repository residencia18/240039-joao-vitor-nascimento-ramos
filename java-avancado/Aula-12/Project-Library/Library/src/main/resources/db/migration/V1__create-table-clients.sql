CREATE TABLE clients (
    id SERIAL PRIMARY KEY,
    name varchar(100) not null,
    email VARCHAR(255) unique,
    cpf VARCHAR(14) NOT NULL unique,
    phone_number VARCHAR(20) NOT NULL,
    activated BOOLEAN NOT NULL,
    public_place varchar(100) not null,
    neighborhood varchar(100) not null,
    cep varchar(9) not null,
    complement varchar(100),
    number varchar(20),
    uf char(2) not null,
    city varchar(100) not null
);
