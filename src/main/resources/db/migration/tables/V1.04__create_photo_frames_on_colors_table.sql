create table photo_frames_on_colors
(
    id             bigint primary key auto_increment,
    photo_frame_id bigint not null,
    color_id       bigint not null
);