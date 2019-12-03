create table discounts(
    id              bigint primary key auto_increment,
    percent_count   int,
    start_date      timestamp ,
    end_date        timestamp,
    photo_frame_id  bigint
);