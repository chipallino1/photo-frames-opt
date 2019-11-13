create table photo_frames (
    id bigint not null auto_increment,
    border_material varchar(100),
    border_width integer,
    cost integer,
    description varchar(300),
    inside_material varchar(100),
    name varchar(100),
    thickness integer,
    user_id bigint not null,
    vendor_code varchar(100),
    primary key (id)
);

create table photo_frames_on_carts (
    id bigint not null auto_increment,
    cart_id bigint not null,
    photo_frame_id bigint not null,
    primary key (id)
);

create table photo_frames_on_colors (
    id bigint not null auto_increment,
    color_id bigint not null,
    photo_frame_id bigint not null,
    primary key (id)]
);

create table photo_frames_on_photos (
    id bigint not null auto_increment,
    photo_frame_id bigint not null,
    photo_id bigint not null,
    primary key (id)
);

create table photo_frames_on_sizes (
    id bigint not null auto_increment,
    photo_frame_id bigint not null,
    size_id bigint not null,
    primary key (id)
);

create table cart (
    id bigint not null auto_increment,
    insert_at date,
    name varchar(300),
    client_id bigint not null,
    primary key (id)
);

create table colors (
    id bigint not null auto_increment,
    name varchar(100),
    rgb varchar(15),
    primary key (id)
);

create table photos (
    id bigint not null auto_increment,
    height integer,
    photo_frame_id bigint not null,
    photo_src varchar(1000),
    width integer,
    primary key (id)
);

create table sizes (
    id bigint not null auto_increment,
    format varchar(10),
    height integer,
    width integer,
    primary key (id)
);

create table users (
    id bigint not null auto_increment,
    email varchar(100) not null unique ,
    first_name varchar(100),
    last_name varchar(100),
    password_encrypted varchar(400),
    phone_number varchar(20),
    role varchar(20),
    primary key (id)
);