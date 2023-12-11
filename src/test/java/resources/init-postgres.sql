CREATE TABLE IF NOT EXISTS hospitals
(
    id        serial primary key,
    name      varchar(255) not null,
    address   varchar(255),
    estimated timestamp default now()
);

CREATE TABLE IF NOT EXISTS doctors
(
    id          serial primary key,
    name        varchar(255) not null,
    salary      integer,
    hospital_id integer
);

CREATE TABLE IF NOT EXISTS patients
(
    id      serial primary key,
    name    varchar(255) not null,
    age     integer,
    address varchar(255)
);


CREATE TABLE IF NOT EXISTS doctor_patient
(
    doctors_id  integer not null references doctors on delete cascade,
    patients_id integer not null references patients on delete cascade,
    primary key (doctors_id, patients_id)
);

INSERT INTO hospitals (name, address, estimated)
VALUES ('Hertzen', 'address 3', '2023-11-14 20:46:41.113103'),
       ('Blokhin', 'address 2', '2023-11-15 03:42:21.961245'),
       ('Sechenov', 'address 1', '2023-11-14 20:46:41.113103');

INSERT INTO doctors (name, salary, hospital_id)
VALUES ('Ivanov', 100, 1),
       ('Petrov', 120, 2),
       ('Pirogov', 90, 1),
       ('Hirurgov', 90, 2),
       ('Svetlov', 100, 3),
       ('Terapevtov', 90, 1),
       ('Terapevtova', 90, 1);

INSERT INTO patients (name, age, address)
VALUES ('Vasya', 30, 'address-1-1'),
       ('Natasha', 35, 'address-1-4'),
       ('Nikita', 45, 'address-1-5'),
       ('Yulia', 23, 'address-1-6'),
       ('Misha', 30, 'address-1-7'),
       ('Kolya', 19, 'address-1-8'),
       ('Felix', 30, 'address101');


INSERT INTO public.doctor_patient (doctors_id, patients_id)
VALUES (1, 2),
       (2, 1),
       (2, 4),
       (2, 2),
       (4, 6),
       (5, 7),
       (5, 3),
       (3, 3),
       (5, 6);
