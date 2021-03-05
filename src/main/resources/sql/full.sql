BEGIN;

DROP TABLE IF EXISTS customers CASCADE;
CREATE TABLE customers (id bigserial PRIMARY KEY, fullname VARCHAR(255));
INSERT INTO customers (fullname) VALUES
('Vasya'),
('Stas'),
('Ilya'),
('Vova'),
('Kolya');

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, title VARCHAR(255), cost numeric(6, 2));
INSERT INTO products (title, cost) VALUES
('Milk', 35.20),
('Bread', 24.80),
('Orange', 45.60);

DROP TABLE IF EXISTS customers_products CASCADE;
CREATE TABLE customers_products (customer_id bigint, product_id bigint, FOREIGN KEY (customer_id) REFERENCES customers (id), FOREIGN KEY (product_id) REFERENCES products (id));
INSERT INTO customers_products (customer_id, product_id) VALUES
(1, 1),
(1, 2),
(2, 3),
(2, 1),
(3, 2),
(3, 3),
(4, 1),
(4, 2),
(5, 3),
(5, 1),
(1, 2),
(2, 3);

COMMIT;