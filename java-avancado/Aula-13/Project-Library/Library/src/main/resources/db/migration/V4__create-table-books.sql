CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    ano_publicacao INTEGER,
    author_id INTEGER REFERENCES authors(id),
    publisher_id INTEGER REFERENCES publishers(id),
    activated BOOLEAN NOT NULL
);