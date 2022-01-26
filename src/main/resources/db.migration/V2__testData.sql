--- Insert data

SET SEARCH_PATH TO currencyexchange;

INSERT INTO roles (name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');

INSERT INTO users (username, password, email)
VALUES ('user', '$2a$12$IRfEDINVX0toTehn0QbXZuRQQ3DtP6OGE73fue06hwNbu5E3X25rG', 'user@gmail.com');

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 2);