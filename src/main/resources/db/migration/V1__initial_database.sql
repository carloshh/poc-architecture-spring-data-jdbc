create table user
(
    id       int auto_increment,
    username varchar(255) not null,
    constraint user_id_uindex
        unique (id)
);

alter table user
    add primary key (id);
