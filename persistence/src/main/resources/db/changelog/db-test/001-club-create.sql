--liquibase formatted sql

--changeset Badr04-dev:001-club-create
--comment: Create table CLUB

CREATE TABLE CLUB (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    club_name VARCHAR(50) NOT NULL UNIQUE,
    coach_id INT NOT NULL,
    stadium_name VARCHAR(100) NOT NULL
);
