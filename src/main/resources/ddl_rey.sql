-- DROP TABLE IF EXISTS ecommerce.users CASCADE;
-- DROP TABLE IF EXISTS ecommerce.products CASCADE;
-- DROP TABLE IF EXISTS ecommerce.shopping_cart CASCADE;
-- DROP TABLE IF EXISTS ecommerce.cart_items CASCADE;
-- DROP TABLE IF EXISTS ecommerce.order_history CASCADE;
-- DROP TABLE IF EXISTS ecommerce.order_items CASCADE;
-- DROP TABLE IF EXISTS ecommerce.reviews CASCADE;
-- DROP TABLE IF EXISTS ecommerce.roles CASCADE;

CREATE TABLE roles (
    "role_id" VARCHAR PRIMARY KEY,
    "name" VARCHAR NOT NULL
);

CREATE TABLE users(
    "id" VARCHAR PRIMARY KEY,
    "username" VARCHAR NOT NULL UNIQUE,
    "password" VARCHAR NOT NULL,
    "role_id" VARCHAR NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE shopping_cart(
    cart_id VARCHAR PRIMARY KEY,
    user_id VARCHAR NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE products(
    product_id VARCHAR PRIMARY KEY,
    "name" VARCHAR NOT NULL,
    "description" VARCHAR NOT NULL,
    price MONEY,
    rating INT,
    category1 VARCHAR NOT NULL,
    category2 VARCHAR NOT NULL
);

CREATE TABLE cart_items(
    id VARCHAR PRIMARY KEY,
    quantity int NOT NULL,
    price MONEY NOT NULL,
    cart_id VARCHAR NOT NULL,
    product_id VARCHAR NOT NULL,
    FOREIGN KEY (cart_id) REFERENCES shopping_cart (cart_id),
    FOREIGN KEY (product_id) REFERENCES products (product_id)
);

CREATE TABLE order_history(
    order_history_id varchar PRIMARY KEY,
    user_id VARCHAR NOT NULL,
    created_date TIMESTAMP NOT NULL,
    total_cost MONEY NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE order_items(
    order_items_id varchar PRIMARY KEY,
    quantity INT NOT NULL,
    price MONEY NOT NULL,
    order_history_id VARCHAR NOT NULL,
    product_id VARCHAR NOT NULL,
    FOREIGN KEY (order_history_id) REFERENCES order_history (order_history_id),
    FOREIGN KEY (product_id) REFERENCES products (product_id)
);

CREATE TABLE reviews(
    review_id VARCHAR PRIMARY KEY,
    product_id VARCHAR NOT NULL,
    "username" VARCHAR NOT NULL,
    "message" VARCHAR NOT NULL,
    rating int,
    FOREIGN KEY (product_id) REFERENCES products (product_id)
);
