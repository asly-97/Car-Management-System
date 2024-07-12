
CREATE TABLE IF NOT EXISTS person(
    id serial primary key,
    first_name varchar(14),
    last_name varchar(14));

CREATE TABLE IF NOT EXISTS car(
    id serial primary key,
    make varchar(14),
    model varchar(14),
    color varchar(14),
    passengers smallint,
    owner_id int,
    constraint owner_id_fk foreign key(owner_id) references person(id)
);


insert into person(first_name, last_name)
values ('Emma', 'Johnson'),
       ('Liam', 'Smith'),
       ('Olivia', 'Brown'),
       ('Noah', 'Davis'),
       ('Ava', 'Martinez');


insert into car(make, model, color, passengers)
values
    ('Toyota', 'Camry', 'Blue', 5),
    ('Honda', 'Civic', 'Red', 5),
    ('Ford', 'Mustang', 'Black', 4),
    ('Chevrolet', 'Malibu', 'White', 5),
    ('Nissan', 'Altima', 'Silver', 5),
    ('BMW', 'X5', 'Gray', 5),
    ('Tesla', 'Model 3', 'White', 5);
