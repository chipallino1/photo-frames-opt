create table photo_frames_common
(
    id             bigint primary key auto_increment,
    photo_src      varchar(1000),
    cost           int,
    photo_frame_id bigint not null,
    size_id        bigint not null,
    color_id       bigint not null
);