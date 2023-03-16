CREATE TABLE tickets
(
    id          UUID PRIMARY KEY,
    customer_id serial,
    concern     VARCHAR(255),
    status      VARCHAR(255),
    timestamp   timestamp,
    FOREIGN KEY (customer_id) references customers (id)
);

CREATE TABLE customers
(
    id          serial PRIMARY KEY,
    name        VARCHAR(255),
    place       VARCHAR(255),
    phone_number VARCHAR(255)
);