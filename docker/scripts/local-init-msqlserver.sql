-- run with user sa

create database [java-apps];
go
use [java-apps];
go
create schema [user-service];
go
create login [user-service] with password = 'user-service-password-12345';
go
create user [user-service] for login [user-service];
go
alter user [user-service] with default_schema=[user-service];
go
grant control on schema::[user-service] to [user-service];
go
grant create table to [user-service];
go