--- DDL

CREATE SCHEMA IF NOT EXISTS currencyexchange;

SET SEARCH_PATH TO currencyexchange;

CREATE TABLE IF NOT EXISTS users
(
    id       BIGSERIAL,
    username VARCHAR(30) NOT NULL,
    password VARCHAR(80),
    email    VARCHAR(50) UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS roles
(
    id   SERIAL,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS users_roles
(
    user_id BIGINT NOT NULL,
    role_id INT    NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

