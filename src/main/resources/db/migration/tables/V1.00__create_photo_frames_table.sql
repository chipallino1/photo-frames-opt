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
    status                    varchar(100),
    user_id                   bigint not null,
    currency_id               bigint not null,
    photo_frames_on_colors_id bigint not null,
    photo_frames_on_sizes_id  bigint not null,
    photo_frames_on_photos_id bigint not null
);