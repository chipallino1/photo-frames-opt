create table users
(
    id                 bigint primary key auto_increment,
    first_name         varchar(300),
    last_name          varchar(300),
    email              varchar(300) unique,
    phone_number       varchar(20) unique,
    password_encrypted varchar(400),
    status             varchar(100)
);