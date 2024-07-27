DROP DATABASE IF EXISTS bikerent;
CREATE DATABASE bikerent;

USE bikerent;

DROP TABLE IF EXISTS role;
CREATE TABLE roles
(
    role_id     INT UNSIGNED AUTO_INCREMENT,
    role_name   VARCHAR(32) NOT NULL UNIQUE,
    PRIMARY KEY (role_id)
);

INSERT INTO roles (role_name) VALUES
    ('client'),
    ('rental_company'),
    ('admin');

DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    user_id     CHAR(36)        NOT NULL,
    name        VARCHAR(100)    NOT NULL,
    email       VARCHAR(100)    NOT NULL UNIQUE,
    password    VARCHAR(100)    NOT NULL,
    user_role   INT UNSIGNED    NOT NULL,

    PRIMARY KEY (user_id),
    FOREIGN KEY (user_role) REFERENCES roles(role_id)
);

SET @client_role_id = (SELECT role_id FROM roles WHERE role_name = 'client');
SET @company_role_id = (SELECT role_id FROM roles WHERE role_name = 'rental_company');
SET @admin_role_id = (SELECT role_id FROM roles WHERE role_name = 'admin');

INSERT INTO user (user_id, name, email, password, user_role) VALUES
    ('b78aeae8-c0ab-48cf-9e01-d68a7e4829a1', 'João da Silva', 'user1@example.com', '123456', @client_role_id),
    ('606a3555-0642-4788-946e-5d0a8b5d2c07', 'Maria Oliveira', 'user2@example.com', 'abcdef', @client_role_id),
    ('1b2e7f6f-580e-41f2-9f20-dea753d397bd', 'Pedro Santos', 'user3@example.com', 'senha123', @client_role_id),
    ('78066f1d-62cd-4371-8fcb-1fbc85dea8ec', 'Ana Pereira', 'user4@example.com', '123321', @client_role_id),
    ('7cbcf887-3617-4e40-bbfb-d653a1b4049d', 'Carlos Eduardo', 'user5@example.com', 'qwerty', @client_role_id),
    ('2936f446-d627-4786-adfd-582e3b741192', 'OiBike', 'oibike@example.com', '123456', @company_role_id),
    ('eb67bced-b73a-4d7f-ba10-1418cc3f9578', 'Sportix', 'sportix@example.com', 'abcdef', @company_role_id),
    ('cb23d5a4-3124-497a-a727-ddcdfb3cb1c4', 'CicloBike', 'ciclobike@example.com', 'qwerty', @company_role_id),
    ('07727f19-7f03-46c8-bc55-ef13a3e411dc', 'Admin1', 'admin1@example.com', 'admin123', @admin_role_id);

DROP TABLE IF EXISTS client;
CREATE TABLE client
(
    client_id   CHAR(36)                        NOT NULL,
    cpf         CHAR(11)                        NOT NULL UNIQUE,
    phone       VARCHAR(11)                     NOT NULL UNIQUE,
    gender      ENUM('Male', 'Female', 'Other') NOT NULL,
    birth       DATE                            NOT NULL,
    PRIMARY KEY (client_id),
    FOREIGN KEY (client_id) REFERENCES user(user_id) ON DELETE CASCADE ON UPDATE CASCADE     
);

INSERT INTO client (client_id, cpf, phone, gender, birth) VALUES
    ('b78aeae8-c0ab-48cf-9e01-d68a7e4829a1', '12345678901', '11999887766', 'Male', '1990-05-15'),
    ('606a3555-0642-4788-946e-5d0a8b5d2c07', '12345678902', '21999887766', 'Female', '1985-03-20'),
    ('1b2e7f6f-580e-41f2-9f20-dea753d397bd', '12345678903', '31999887766', 'Male', '1995-11-10'),
    ('78066f1d-62cd-4371-8fcb-1fbc85dea8ec', '12345678904', '41999887766', 'Female', '1980-07-05'),
    ('7cbcf887-3617-4e40-bbfb-d653a1b4049d', '12345678905', '51999887766', 'Other', '2000-09-25');

DROP TABLE IF EXISTS rental_company;
CREATE TABLE rental_company
(
    company_id  CHAR(36)        NOT NULL,   
    cnpj        CHAR(14)        NOT NULL UNIQUE,
    city        VARCHAR(100)    NOT NULL,
    PRIMARY KEY (company_id),
    FOREIGN KEY (company_id) REFERENCES user(user_id) ON DELETE CASCADE ON UPDATE CASCADE  
);

INSERT INTO rental_company (company_id, cnpj, city) VALUES
    ('2936f446-d627-4786-adfd-582e3b741192', '12345678000100', 'São Carlos'),
    ('eb67bced-b73a-4d7f-ba10-1418cc3f9578', '12345678000200', 'Araraquara'),
    ('cb23d5a4-3124-497a-a727-ddcdfb3cb1c4', '12345678000300', 'São Carlos');

DROP TABLE IF EXISTS admin;
CREATE TABLE admin
(
    admin_id    CHAR(36)    NOT NULL,
    PRIMARY KEY (admin_id),
    FOREIGN KEY (admin_id) REFERENCES user(user_id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO admin (admin_id) VALUES
    ('07727f19-7f03-46c8-bc55-ef13a3e411dc');

DROP TABLE IF EXISTS bike_rental;
CREATE TABLE bike_rental
(
    rental_id   CHAR(36)    NOT NULL,
    client_id   CHAR(36)    NOT NULL,
    company_id  CHAR(36)    NOT NULL,
    date_hour   DATETIME    NOT NULL,
    PRIMARY KEY (rental_id),
    FOREIGN KEY (client_id)    REFERENCES client(client_id),
    FOREIGN KEY (company_id)  REFERENCES rental_company(company_id)
);

INSERT INTO bike_rental (rental_id, client_id, company_id, date_hour) VALUES
    ('786d0836-7156-4621-ac2e-159c53cc1f17', 'b78aeae8-c0ab-48cf-9e01-d68a7e4829a1', '2936f446-d627-4786-adfd-582e3b741192', '2024-07-27 09:00:00.000000'),
    ('4e7f20be-c986-4709-a445-27a9659519d3', 'b78aeae8-c0ab-48cf-9e01-d68a7e4829a1', 'eb67bced-b73a-4d7f-ba10-1418cc3f9578', '2024-07-27 10:00:00.000000'),
    ('bc42b4a5-1a5e-4d33-8996-2dbb74259deb', '606a3555-0642-4788-946e-5d0a8b5d2c07', '2936f446-d627-4786-adfd-582e3b741192', '2024-07-28 11:00:00.000000'),
    ('afb2b549-142c-4f84-90ad-e4c8467de8cc', '1b2e7f6f-580e-41f2-9f20-dea753d397bd', 'cb23d5a4-3124-497a-a727-ddcdfb3cb1c4', '2024-07-28 12:00:00.000000');