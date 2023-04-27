CREATE SEQUENCE tinder.persons_s;

CREATE TABLE tinder.persons
(
    id            BIGINT NOT NULL CONSTRAINT persons_pk PRIMARY KEY,
    chat_id       BIGINT,
    nickname      VARCHAR,
    gender        VARCHAR,
    orientation   VARCHAR,
    age           BIGINT,
    header        VARCHAR,
    description   VARCHAR
);