create table orders
(
    id             bigint primary key auto_increment,
    user_id        bigint not null,
    order_date     timestamp not null,
    status         varchar(100),
    comment        varchar(300)
);