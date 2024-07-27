DROP DATABASE IF EXISTS bikerent;

CREATE DATABASE bikerent;

USE bikerent;

DROP TABLE IF EXISTS client;
CREATE TABLE client
(
    uuid        CHAR(36)                        NOT NULL,
    name        VARCHAR(100)                    NOT NULL,
    email       VARCHAR(100)                    NOT NULL UNIQUE,
    password    VARCHAR(100)                    NOT NULL,
    cpf         CHAR(11)                        NOT NULL UNIQUE,
    phone       VARCHAR(11)                     NOT NULL UNIQUE,
    gender      ENUM("Male", "Female", "Other") NOT NULL,
    birth       DATE                            NOT NULL,
    PRIMARY KEY (uuid)
);

INSERT INTO client (uuid, name, email, password, cpf, phone, gender, birth) VALUES
    ("b78aeae8-c0ab-48cf-9e01-d68a7e4829a1", "João da Silva", "user1@example.com", "123456", "12345678901", "11999887766", "Male", "1990-05-15"),
    ("606a3555-0642-4788-946e-5d0a8b5d2c07", "Maria Oliveira", "user2@example.com", "abcdef", "12345678902", "21999887766", "Female", "1985-03-20"),
    ("1b2e7f6f-580e-41f2-9f20-dea753d397bd", "Pedro Santos", "user3@example.com", "senha123", "12345678903", "31999887766", "Male", "1995-11-10"),
    ("78066f1d-62cd-4371-8fcb-1fbc85dea8ec", "Ana Pereira", "user4@example.com", "123321", "12345678904", "41999887766", "Female", "1980-07-05"),
    ("7cbcf887-3617-4e40-bbfb-d653a1b4049d", "Carlos Eduardo", "user5@example.com", "qwerty", "12345678905", "51999887766", "Other", "2000-09-25");

DROP TABLE IF EXISTS rental_company;
CREATE TABLE rental_company
(
    uuid        CHAR(36)        NOT NULL,   
    name        VARCHAR(100)    NOT NULL,
    email       VARCHAR(100)    NOT NULL,
    password    VARCHAR(100)    NOT NULL,
    cnpj        CHAR(14)        NOT NULL UNIQUE,
    city        VARCHAR(100)    NOT NULL,
    PRIMARY KEY (uuid)
);

INSERT INTO rental_company(uuid, name, email, password, cnpj, city) VALUES
    ("2936f446-d627-4786-adfd-582e3b741192", "OiBike", "oibike@example.com", "123456", "12345678000100", "São Carlos"),
    ("eb67bced-b73a-4d7f-ba10-1418cc3f9578", "Sportix", "sportix@example.com", "abcdef", "12345678000200", "Araraquara"),
    ("cb23d5a4-3124-497a-a727-ddcdfb3cb1c4", "CicloBike", "ciclobike@example.com", "qwerty", "12345678000300", "São Carlos");

DROP TABLE IF EXISTS bike_rental;
CREATE TABLE bike_rental
(
    uuid            CHAR(36)    NOT NULL,
    client_cpf      CHAR(11)    NOT NULL,
    company_cnpj    CHAR(14)    NOT NULL,
    date_hour       DATETIME    NOT NULL,
    PRIMARY KEY (uuid)
)