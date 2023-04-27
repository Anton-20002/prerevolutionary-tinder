CREATE SEQUENCE tinder.persons_s;

CREATE TABLE tinder.persons
(
    id            BIGINT NOT NULL CONSTRAINT persons_pk PRIMARY KEY,
    nickname      VARCHAR,
    gender        VARCHAR,
    orientation   VARCHAR,
    age           BIGINT,
    header        VARCHAR,
    description   VARCHAR
);