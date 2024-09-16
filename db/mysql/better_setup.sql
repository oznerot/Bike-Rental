DROP DATABASE IF EXISTS bikerent;
CREATE DATABASE bikerent;

USE bikerent;

DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    user_id     INT UNSIGNED    NOT NULL AUTO_INCREMENT,
    name        VARCHAR(100)    NOT NULL,
    email       VARCHAR(100)    NOT NULL UNIQUE,
    password    VARCHAR(100)    NOT NULL,
    user_role   VARCHAR(7)      NOT NULL,

    PRIMARY KEY (user_id)
);

INSERT INTO user (name, email, password, user_role) VALUES ('Admin1', 'admin1@example.com', 'admin123', 'ADMIN');

DROP TABLE IF EXISTS client;
CREATE TABLE client
(
    client_id   INT UNSIGNED                    NOT NULL,
    cpf         CHAR(11)                        NOT NULL UNIQUE,
    phone       VARCHAR(11)                     NOT NULL UNIQUE,
    gender      ENUM('M', 'F', 'Other')         NOT NULL,
    birth       DATE                            NOT NULL,
    PRIMARY KEY (client_id),
    FOREIGN KEY (client_id) REFERENCES user(user_id) ON DELETE CASCADE ON UPDATE CASCADE     
);

INSERT INTO user (name, email, password, user_role) VALUES ('João da Silva', 'user1@example.com', '123456', 'CLIENT');
SET @last_user_id = LAST_INSERT_ID();
INSERT INTO client (client_id, cpf, phone, gender, birth) VALUES (@last_user_id, '12345678901', '11999887766', 'M', '1990-05-15');

INSERT INTO user (name, email, password, user_role) VALUES ('Maria Oliveira', 'user2@example.com', 'abcdef', 'CLIENT');
SET @last_user_id = LAST_INSERT_ID();
INSERT INTO client (client_id, cpf, phone, gender, birth) VALUES (@last_user_id, '12345678902', '21999887766', 'F', '1985-03-20');

INSERT INTO user (name, email, password, user_role) VALUES ('Pedro Santos', 'user3@example.com', 'senha123', 'CLIENT');
SET @last_user_id = LAST_INSERT_ID();
INSERT INTO client (client_id, cpf, phone, gender, birth) VALUES (@last_user_id, '12345678903', '31999887766', 'M', '1995-11-10');

INSERT INTO user (name, email, password, user_role) VALUES ('Ana Pereira', 'user4@example.com', '123321', 'CLIENT');
SET @last_user_id = LAST_INSERT_ID();
INSERT INTO client (client_id, cpf, phone, gender, birth) VALUES (@last_user_id, '12345678904', '41999887766', 'F', '1980-07-05');

INSERT INTO user (name, email, password, user_role) VALUES ('Carlos Eduardo', 'user5@example.com', 'qwerty', 'CLIENT');
SET @last_user_id = LAST_INSERT_ID();
INSERT INTO client (client_id, cpf, phone, gender, birth) VALUES (@last_user_id, '12345678905', '51999887766', 'Other', '2000-09-25');

DROP TABLE IF EXISTS rental_company;
CREATE TABLE rental_company
(
    company_id  INT UNSIGNED    NOT NULL,   
    cnpj        CHAR(14)        NOT NULL UNIQUE,
    city        VARCHAR(100)    NOT NULL,
    PRIMARY KEY (company_id),
    FOREIGN KEY (company_id) REFERENCES user(user_id) ON DELETE CASCADE ON UPDATE CASCADE  
);

INSERT INTO user (name, email, password, user_role) VALUES ('OiBike', 'oibike@example.com', '123456', 'COMPANY');
SET @last_company_id = LAST_INSERT_ID();
INSERT INTO rental_company (company_id, cnpj, city) VALUES (@last_company_id, '12345678000100', 'São Carlos');

INSERT INTO user (name, email, password, user_role) VALUES ('Sportix', 'sportix@example.com', 'abcdef', 'COMPANY');
SET @last_company_id = LAST_INSERT_ID();
INSERT INTO rental_company (company_id, cnpj, city) VALUES (@last_company_id, '12345678000200', 'Araraquara');

INSERT INTO user (name, email, password, user_role) VALUES ('CicloBike', 'ciclobike@example.com', 'qwerty', 'COMPANY');
SET @last_company_id = LAST_INSERT_ID();
INSERT INTO rental_company (company_id, cnpj, city) VALUES (@last_company_id, '12345678000300', 'São Carlos');

DROP TABLE IF EXISTS bike_rental;
CREATE TABLE bike_rental
(
    rental_id   INT UNSIGNED   NOT NULL AUTO_INCREMENT,
    client_id   INT UNSIGNED   NOT NULL,
    company_id  INT UNSIGNED   NOT NULL,
    date_hour   DATETIME       NOT NULL,
    PRIMARY KEY (rental_id),
    FOREIGN KEY (client_id)    REFERENCES client(client_id) ON DELETE CASCADE,
    FOREIGN KEY (company_id)  REFERENCES rental_company(company_id) ON DELETE CASCADE
);

INSERT INTO bike_rental (client_id, company_id, date_hour) VALUES
    ((SELECT client_id FROM client WHERE cpf = '12345678901'), (SELECT company_id FROM rental_company WHERE cnpj = '12345678000100'), '2024-07-27 09:00:00.000000'),
    ((SELECT client_id FROM client WHERE cpf = '12345678901'), (SELECT company_id FROM rental_company WHERE cnpj = '12345678000200'), '2024-07-27 10:00:00.000000'),
    ((SELECT client_id FROM client WHERE cpf = '12345678901'), (SELECT company_id FROM rental_company WHERE cnpj = '12345678000300'), '2024-07-28 11:00:00.000000'),
    ((SELECT client_id FROM client WHERE cpf = '12345678902'), (SELECT company_id FROM rental_company WHERE cnpj = '12345678000100'), '2024-07-28 12:00:00.000000');