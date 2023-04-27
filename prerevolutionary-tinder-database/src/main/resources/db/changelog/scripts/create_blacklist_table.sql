CREATE SEQUENCE tinder.blacklist_s;

CREATE TABLE tinder.blacklist
(
       id                  BIGINT NOT NULL CONSTRAINT blacklist_pk PRIMARY KEY,
       person_id           BIGINT,
       banned_person_id    BIGINT
);