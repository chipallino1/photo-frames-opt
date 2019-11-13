create table photo_frames (
    id bigint           not null auto_increment,
    border_material     varchar(100),
    border_width        integer,
    cost                integer,
    description         varchar(300),
    inside_material     varchar(100),
    name                varchar(100),
    thickness           integer,
    user_id bigint      not null,
    vendor_code         varchar(100),
    primary key (id)
);