--liquibase formatted sql

--changeset Badr04-dev:005-stadium-insert
--comment: Inset data in table STADIUM

INSERT INTO STADIUM (NAME, CITY, COUNTRY, NUMBER_OF_SEATS) VALUES ('Santiago Bernabio', 'Madrid', 'Spain', 80000);