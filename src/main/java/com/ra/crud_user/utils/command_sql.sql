use test_db;

create table users (
    id int auto_increment primary key ,
    name varchar(255),
    dateOfBirth date,
    address varchar(255),
    phone varchar(255)
);