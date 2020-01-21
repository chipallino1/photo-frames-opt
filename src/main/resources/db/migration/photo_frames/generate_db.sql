drop database ramki_opt;
create database ramki_opt;
use ramki_opt;

create table photo_frames
(
    id              bigint primary key auto_increment,
    name            varchar(300),
    vendor_code     varchar(300),
    border_material varchar(300),
    inside_material varchar(300),
    border_width    int,
    thickness       int,
    cost            int,
    description     varchar(300),
    row_status      varchar(100),
    user_id         bigint not null,
    popularity      bigint
);

create table sizes
(
    id     bigint primary key auto_increment,
    format varchar(300),
    width  int,
    height int
);

create table photo_frames_common
(
    id             bigint primary key auto_increment,
    photo_src      varchar(1000),
    cost           int,
    photo_frame_id bigint not null,
    size_id        bigint not null,
    color_id       bigint not null
);

create table colors
(
    id   bigint primary key auto_increment,
    name varchar(300),
    rgb  varchar(300)
);

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

create table carts
(
    id        bigint primary key auto_increment,
    name      varchar(300),
    client_id bigint not null,
    insert_at date
);

create table photo_frames_on_carts
(
    id             bigint primary key auto_increment,
    cart_id        bigint not null,
    photo_frame_id bigint not null
);

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

create table photo_frames_on_orders
(
    id                 bigint primary key auto_increment,
    photo_frame_id     bigint not null,
    photo_frames_count int    not null,
    order_id           bigint not null
);

create table discounts
(
    id             bigint primary key auto_increment,
    percent_count  int,
    start_date     timestamp,
    end_date       timestamp,
    photo_frame_id bigint
);

create table roles
(
    id   bigint not null primary key,
    name varchar(100)
);

create table users_on_roles
(
    id                bigint not null primary key,
    user_id           bigint not null,
    role_id bigint not null
);

ALTER TABLE photo_frames ADD FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE photo_frames_on_carts ADD FOREIGN KEY (photo_frame_id) REFERENCES photo_frames(id);
ALTER TABLE photo_frames_on_carts ADD FOREIGN KEY (cart_id) REFERENCES carts(id);

ALTER TABLE carts ADD FOREIGN KEY (client_id) REFERENCES users(id);

ALTER TABLE orders ADD FOREIGN KEY (client_id) REFERENCES users(id);

ALTER TABLE photo_frames_on_orders ADD FOREIGN KEY (order_id) REFERENCES orders(id);
ALTER TABLE photo_frames_on_orders ADD FOREIGN KEY (photo_frame_id) REFERENCES photo_frames(id);

ALTER TABLE photo_frames_common ADD FOREIGN KEY (photo_frame_id) REFERENCES photo_frames(id);
ALTER TABLE photo_frames_common ADD FOREIGN KEY (size_id) REFERENCES sizes(id);
ALTER TABLE photo_frames_common ADD FOREIGN KEY (color_id) REFERENCES colors(id);

ALTER TABLE discounts ADD FOREIGN KEY (photo_frame_id) REFERENCES photo_frames(id);

ALTER TABLE users_on_roles ADD FOREIGN KEY (user_id) REFERENCES users(id);
ALTER TABLE users_on_roles ADD FOREIGN KEY (role_id) REFERENCES roles(id);
