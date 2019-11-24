create table photo_frames_on_carts
(
    id             bigint primary key auto_increment,
    cart_id        bigint not null,
    photo_frame_id bigint not null
);