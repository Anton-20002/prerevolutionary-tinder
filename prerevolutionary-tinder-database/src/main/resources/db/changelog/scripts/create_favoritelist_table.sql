CREATE SEQUENCE tinder.favoritelist_s;

CREATE TABLE tinder.favoritelist
(
       id                  BIGINT NOT NULL CONSTRAINT favoritelist_pk PRIMARY KEY,
       person_id           BIGINT,
       favorite_person_id  BIGINT,
       romance_status      VARCHAR
);