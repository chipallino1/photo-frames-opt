create table cart
(
    id        bigint primary key auto_increment,
    name      varchar(300),
    client_id bigint not null,
    insert_at date
);