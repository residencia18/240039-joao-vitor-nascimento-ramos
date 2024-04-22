CREATE TABLE sale_product(
    id SERIAL PRIMARY KEY,
    id_sale BIGINT,
    id_product BIGINT,
    amount BIGINT,
    FOREIGN KEY(id_sale) REFERENCES sales(id),
    FOREIGN KEY(id_product) REFERENCES products(id)
)