CREATE TABLE roles (
    id BIGSERIAL  PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL
);