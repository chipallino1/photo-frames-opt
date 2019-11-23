create table photos
(
    id             bigint primary key auto_increment,
    photo_src      varchar(1000),
    width          int,
    height         int,
    photo_frame_id bigint not null
);