DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS ratings;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS products;


CREATE TABLE products
(
    id    SERIAL,
    name  varchar(20)    NOT NULL,
    price numeric(10, 2) NOT NULL,

    CONSTRAINT products_pk PRIMARY KEY (id)
);
CREATE TABLE users
(
    username varchar(20) NOT NULL,
    password varchar(25) NOT NULL,
    role     varchar(10)  NOT NULL,
    category varchar(10) NOT NULL,

    CONSTRAINT users_pk PRIMARY KEY (username)
);

CREATE TABLE ratings
(
    username    varchar(20) NOT NULL,
    productName varchar(25) NOT NULL,
    product_id  int         NOT NULL,
    rate        rating,

    CONSTRAINT rates_pk PRIMARY KEY (username, product_id),
    FOREIGN KEY (username) REFERENCES users (username),
    FOREIGN KEY (product_id) REFERENCES products (id)
);

CREATE TABLE comments
(
    id         SERIAL,
    product_id int,
    comment    varchar(999),

    CONSTRAINT comments_pk PRIMARY KEY (id),
    FOREIGN KEY (product_id) REFERENCES products (id)
);


    INSERT INTO products (name, price)
    VALUES ('Table', 85000);
    INSERT INTO products (name, price)
    VALUES ('Sofa', 350000);
    INSERT INTO products (name, price)
    VALUES ('Wardrobe', 190000);
    INSERT INTO products (name, price)
    VALUES ('Armchair ', 107000);
    INSERT INTO products (name, price)
    VALUES ('Chair', 40000);

    INSERT INTO users
    VALUES ('Jonatan', 'admin123#', 'ADMIN', 'UNBLOCKED');
    INSERT INTO users
    VALUES ('Kristien', '123', 'USER', 'UNBLOCKED');
    INSERT INTO users
    VALUES ('Angelika', '123', 'USER', 'BLOCKED');


    INSERT INTO ratings (username, productname, product_id, rating)
    VALUES ('Jonatan', 'Chair', 5, 'FIVE');
    INSERT INTO ratings (username, productname, product_id, rating)
    VALUES ('Angelika', 'Wardrobe', 3, 'FOUR');
    INSERT INTO ratings (username, productname, product_id, rating)
    VALUES ('Angelika', 'Table', 1, 'THREE');
    INSERT INTO ratings (username, productname, product_id, rating)
    VALUES ('Kristien', 'Sofa', 2, 'THREE');
    INSERT INTO ratings (username, productname, product_id, rating)
    VALUES ('Jonatan', 'Wardrobe', 3, 'FOUR');
    INSERT INTO ratings (username, productname, product_id, rating)
    VALUES ('Kristien', 'Table', 1, 'ONE');
    INSERT INTO ratings (username, productname, product_id, rating)
    VALUES ('Jonatan', 'Armchair', 4, 'FIVE');
    INSERT INTO ratings (username, productname, product_id, rating)
    VALUES ('Jonatan', 'Table', 1, 'FIVE');


    INSERT INTO comments (product_id, comment)
    VALUES (4, 'What is the price ?!!');
    INSERT INTO comments (product_id, comment)
    VALUES (1, 'Grate product!!');
    INSERT INTO comments (product_id, comment)
    VALUES (2, 'I had one, I recommend');
    INSERT INTO comments (product_id, comment)
    VALUES (5, 'Looks good,but the quality is poor');
    INSERT INTO comments (product_id, comment)
    VALUES (3, 'How can I get it?!');
