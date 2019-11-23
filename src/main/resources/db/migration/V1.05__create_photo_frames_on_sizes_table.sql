create table photo_frames_on_sizes
(
    id             bigint primary key auto_increment,
    photo_frame_id bigint not null,
    size_id        bigint not null
);