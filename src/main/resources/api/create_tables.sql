drop database ramki_opt;
create database ramki_opt;
use ramki_opt;
create table currency
(
    id    bigint primary key auto_increment,
    name  varchar(100),
    icon  varchar(5),
    value double
);

create table photo_frames
(
    id                        bigint primary key auto_increment,
    name                      varchar(300),
    vendor_code               varchar(300),
    border_material           varchar(300),
    inside_material           varchar(300),
    border_width              int,
    thickness                 int,
    cost                      int,
    description               varchar(300),
    user_id                   bigint not null,
    currency_id               bigint not null,
    photo_frames_on_colors_id bigint not null,
    photo_frames_on_sizes_id  bigint not null,
    photo_frames_on_photos_id bigint not null

);

create table sizes
(
    id     bigint primary key auto_increment,
    format varchar(300),
    width  int,
    height int
);

create table photos
(
    id             bigint primary key auto_increment,
    photo_src      varchar(1000),
    size           varchar(100),
    photo_frame_id bigint not null
);

create table colors
(
    id   bigint primary key auto_increment,
    name varchar(300),
    rgb  varchar(300)
);

create table photo_frames_on_colors
(
    id             bigint primary key auto_increment,
    photo_frame_id bigint not null,
    color_id       bigint not null
);

create table photo_frames_on_sizes
(
    id             bigint primary key auto_increment,
    photo_frame_id bigint not null,
    size_id        bigint not null
);

create table photo_frames_on_photos
(
    id             bigint primary key auto_increment,
    photo_frame_id bigint not null,
    photo_id       bigint not null
);

create table users
(
    id                 bigint primary key auto_increment,
    first_name         varchar(300),
    last_name          varchar(300),
    email              varchar(300),
    phone_number       varchar(20),
    role               varchar(300),
    password_encrypted varchar(400)

);

create table cart
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