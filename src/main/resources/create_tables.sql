CREATE TABLE tickets
(
    id          serial PRIMARY KEY,
    customer_id serial,
    concern     VARCHAR(255),
    timestamp   timestamp
);
