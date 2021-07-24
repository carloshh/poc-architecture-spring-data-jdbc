create table user_account
(
    id   int identity
        constraint user_pk
            primary key nonclustered,
    username varchar(256) not null
)
go

create unique index user_id_uindex
    on user_account (id)
go

