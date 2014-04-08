create table Group_( --sqlite doesn't like something called Group
	group_id int primary key,
	name varchar(50) unique
);

create table User(
	user_id int primary key,
	name varchar(50) unique,
	password varchar(50)
);

insert into User (name, password) values ("test", "password");
insert into User (name, password) values ("test2", "password");