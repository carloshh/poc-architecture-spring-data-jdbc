create table user_details(id char(36),user_id int not null,email varchar2(255) not null);
create unique index user_details_unique_index on user_details (id);
alter table user_details add constraint user_details_pk primary key (id);