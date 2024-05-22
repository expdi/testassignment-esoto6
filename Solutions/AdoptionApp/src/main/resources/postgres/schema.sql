DROP ROLE IF EXISTS larku;
CREATE USER larku password 'larku' CREATEDB CREATEROLE;

ALTER ROLE larku WITH LOGIN;
ALTER ROLE larku INHERIT;

ALTER DATABASE adoption OWNER TO larku;

SET ROLE larku;

DROP TABLE IF EXISTS adopters;
DROP TABLE IF EXISTS animals;

CREATE TABLE adopters
(
    id            SERIAL PRIMARY KEY NOT NULL,
    name          varchar(255),
    phone_number  varchar(255),
    email         varchar(255),
    is18          BOOLEAN            NOT NULL,
    date_created  DATE,
    date_modified DATE
);

CREATE TABLE ANIMALS
(
    id           SERIAL PRIMARY KEY NOT NULL,
    name         varchar(255),
    pet_type     varchar(255),
    dob          DATE,
    breed        varchar(255),
    adopted_date DATE,
    adopter_id   integer,
    foreign key (adopter_id) references ADOPTERS (id)
);

CREATE UNIQUE INDEX idx_adopter_id ON ADOPTERS (id ASC);
CREATE UNIQUE INDEX idx_animal_id ON ANIMALS (id ASC);

-- Insert Adopters Data
INSERT INTO ADOPTERS(name, phone_number, email, is18, date_created, date_modified)
VALUES ('Edwin Soto', '111-111-1111', 'edwin.soto@email.com', true, now(), null),
       ('Spouse Soto', '222-222-2222', 'spouse.soto@email.com', true, now(), null),
       ('Kido One Soto', '333-333-3333', 'kido1.soto@email.com', false, now(), null),
       ('Kido Two Soto', '444-444-4444', 'kido2.soto@email.com', false, now(), null),
       ('Kido Three Soto', '555-555-5555', 'kido3.soto@email.com', false, now(), null);

-- Insert Animals Data
INSERT INTO ANIMALS(name, pet_type, dob, breed, adopter_id, adopted_date)
VALUES ('Fred', 'CAT', '2024-01-23', 'Siamese', null, null),
       ('Fido', 'CAT', '2023-07-29', 'persian', null, null),
       ('Gracie', 'DOG', '2022-01-23', 'Terrier Mix', null, null),
       ('Spot', 'DOG', '2023-12-24', 'German Malinois', null, null),
       ('Slimy', 'TURTLE', '2022-03-23', 'Yellow Belly Slider', null, null);