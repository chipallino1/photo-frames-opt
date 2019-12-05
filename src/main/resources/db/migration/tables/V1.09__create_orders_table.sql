create table orders
(
    id             bigint primary key auto_increment,
    user_id        bigint not null,
    order_date     timestamp not null,
    order_status   varchar(100),
    count          int,
    comment        varchar(300),
    photo_frame_id bigint,
    status         varchar (100),
    color          varchar (100),
    format         varchar (10)
);