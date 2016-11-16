drop table groceries  if exists;
create table groceries (
id decimal(5,0) generated by default as identity(start with 1, increment by 1) not null,
completed boolean default false not null,
item_name varchar(255) not null,
date_created varchar(11) not null,
primary key(id)
); 