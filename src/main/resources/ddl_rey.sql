DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS shopping_cart;
DROP TABLE IF EXISTS order_history;

CREATE TABLE users(
    "id" VARCHAR PRIMARY key,
    "username" VARCHAR not null UNIQUE,
    "password" VARCHAR not NULL
);

CREATE TABLE products(
    product_id VARCHAR PRIMARY key,
    "name" varchar not null,
    "description" varchar not null,
    price money,
    rating INT
);

CREATE TABLE shopping_cart(
    cart_id int PRIMARY key,
    product_id VARCHAR not NULL,
    user_id VARCHAR not NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    foreign key (product_id) REFERENCES product (product_id)
);

CREATE table order_history(
    order_history_id varchar PRIMARY KEY,
    user_id VARCHAR not NULL,
    product_id VARCHAR NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    foreign key (product_id) REFERENCES product (product_id)
);

