CREATE TABLE payments(
        id SERIAL PRIMARY KEY,
        id_sale BIGINT UNIQUE,
        payment_type VARCHAR(20),
        date_payment TIMESTAMP,
        FOREIGN KEY(id_sale) REFERENCES sales(id)
)