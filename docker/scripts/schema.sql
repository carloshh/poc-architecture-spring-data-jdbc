create table user_details
(
    id      int auto_increment,
    user_id int          not null,
    email   varchar(255) not null,
    constraint user_details_id_uindex
        unique (id),
    constraint user_details_user_id_uindex
        unique (user_id)
);

alter table user_details
    add primary key (id);

