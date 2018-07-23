DROP TABLE customer IF EXISTS;
DROP TABLE product IF EXISTS;

CREATE TABLE product (
  id             INTEGER IDENTITY PRIMARY KEY,
  name           VARCHAR(255),
  is_advertised  CHAR(1),
  price          NUMERIC(14,2),
  discount       NUMERIC(14,2)
);
CREATE INDEX product_name ON product (name);

CREATE TABLE customer (
  id             INTEGER IDENTITY PRIMARY KEY,
  login          VARCHAR(255),
  is_premium     CHAR(1),
  fav_product_id INTEGER
);
CREATE INDEX customer_login ON customer (login);
ALTER TABLE customer ADD CONSTRAINT fk_fav_product FOREIGN KEY (fav_product_id) REFERENCES product (id);