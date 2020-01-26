create table users_on_roles
  (
      id                bigint not null primary key auto_increment,
      user_id           bigint not null,
      role_id bigint not null
  );