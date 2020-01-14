create table photo_frames_on_orders
(
    id                 bigint primary key auto_increment,
    photo_frame_id     bigint not null,
    photo_frames_count int    not null,
    order_id           bigint not null
);