CREATE SCHEMA IF NOT EXISTS `batch_process`;

USE batch_process;

DROP TABLE people IF EXISTS;

CREATE TABLE people  (
    person_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    first_name VARCHAR(20),
    last_name VARCHAR(20),
    age INT NOT NULL
);