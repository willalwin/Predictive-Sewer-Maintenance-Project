--current location
Show con_name;

--show db names
Select name, con_id 
from v$pdbs;

--alter session to pluggable db
Alter session set container=orclpdb;

--show new location
Show con_name;

--current db and read write info
select name, open_mode from v$pdbs;

--open db
alter PLUGGABLE DATABASE open;

--check current user list
select * from all_users;

--create capstone user
create user capstone identified by capstone;

--grant privileges to capstone user
grant create session to capstone;

grant create table to capstone;

grant unlimited tablespace to capstone;

grant create sequence to capstone;

grant create view to capstone;

grant create synonym to capstone;
