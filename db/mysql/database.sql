DROP DATABASE IF EXISTS bikerent;

CREATE DATABASE bikerent;

USE bikerent;

DROP TABLE IF EXISTS client;
CREATE TABLE client
(
    id          INT unsigned                    NOT NULL AUTO_INCREMENT,
    name        VARCHAR(100)                    NOT NULL,
    email       VARCHAR(100)                    NOT NULL,
    password    VARCHAR(100)                    NOT NULL,
    cpf         CHAR(11)                        NOT NULL UNIQUE,
    phone       VARCHAR(11)                     NOT NULL,
    gender      ENUM("Male", "Female", "Other") NOT NULL,
    birth       DATE                            NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO client (name, email, password, cpf, phone, gender, birth) VALUES
    ("João da Silva", "user1@example.com", "123456", "12345678901", "11999887766", "Male", "1990-05-15"),
    ("Maria Oliveira", "user2@example.com", "abcdef", "12345678902", "21999887766", "Female", "1985-03-20"),
    ("Pedro Santos", "user3@example.com", "senha123", "12345678903", "31999887766", "Male", "1995-11-10"),
    ("Ana Pereira", "user4@example.com", "123321", "12345678904", "41999887766", "Female", "1980-07-05"),
    ("Carlos Eduardo", "user5@example.com", "qwerty", "12345678905", "51999887766", "Other", "2000-09-25");

DROP TABLE IF EXISTS rental_company;
CREATE TABLE rental_company
(
    id          INT unsigned    NOT NULL AUTO_INCREMENT,   
    name        VARCHAR(100)    NOT NULL,
    email       VARCHAR(100)    NOT NULL,
    password    VARCHAR(100)    NOT NULL,
    cnpj        CHAR(14)        NOT NULL UNIQUE,
    city        VARCHAR(100)    NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO rental_company(name, email, password, cnpj, city) VALUES
    ("OiBike", "oibike@example.com", "123456", "12345678000100", "São Carlos"),
    ("Sportix", "sportix@example.com", "abcdef", "12345678000200", "Araraquara"),
    ("CicloBike", "ciclobike@example.com", "qwerty", "12345678000300", "São Carlos");