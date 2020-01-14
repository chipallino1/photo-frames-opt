create table orders
(
    id             bigint primary key auto_increment,
    client_id      bigint    not null,
    order_date     timestamp not null,
    order_status   varchar(100),
    total_cost     int,
    comment        varchar(300),
    row_status     varchar(100)
);