CREATE DATABASE ticket;
CREATE TABLE tickets
(
    id          serial PRIMARY KEY,
    customer_id serial,
    concern     VARCHAR(255),
    status      VARCHAR(255),
    scheduled_timestamp   timestamp,
    creation_timestamp   timestamp,
    place       VARCHAR(100)
--     FOREIGN KEY (customer_id) references customers (id)
);

CREATE TABLE tickets_assigned
(
    id          serial PRIMARY KEY,
    ticket_id   serial,
    consultant_id serial,
--     FOREIGN KEY (consultant_id) references consultants (id),
    FOREIGN KEY (ticket_id) references tickets (id)
);

CREATE TABLE customers
(
    id          serial PRIMARY KEY,
    name        VARCHAR(255),
    place       VARCHAR(255),
    phone_number VARCHAR(255)
);

CREATE TABLE feedbacks
(
    id        serial PRIMARY KEY,
    ticket_id serial,
    rating    int2,
    text      VARCHAR(255),
    FOREIGN KEY (ticket_id) references tickets (id)
);