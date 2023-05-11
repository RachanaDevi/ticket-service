CREATE TABLE if not exists tickets
(
    id                  serial PRIMARY KEY,
    customer_id         serial,
    product_id          serial,
    concern             VARCHAR(255),
    status              VARCHAR(10),
    scheduled_timestamp timestamp,
    creation_timestamp  timestamp,
    place               VARCHAR(20)
--     FOREIGN KEY (customer_id) references customers (id)
);