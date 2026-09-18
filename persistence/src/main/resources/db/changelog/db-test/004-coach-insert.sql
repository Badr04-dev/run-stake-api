--liquibase formatted sql

--changeset Badr04-dev:004-coach-insert
--comment: Insert data in table COACH

INSERT INTO COACH (FIRST_NAME, LAST_NAME, YEARS_OF_EXPERIENCE) VALUES ('José', 'Morinhou', 20);