--liquibase formatted sql

--changeset Badr04-dev:002-stadium-create
--comment: Create table STADIUM

CREATE TABLE STADIUM (
    NAME VARCHAR(100) PRIMARY KEY,
    CITY VARCHAR(50) NOT NULL,
    COUNTRY VARCHAR(50) NOT NULL,
    NUMBER_OF_SEATS INT NOT NULL
);
