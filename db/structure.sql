create table Group_( --sqlite doesn't like something called Group
	id integer primary key autoincrement,
	name varchar(50) unique,
	timetable_id integer foreign key
);

create table User(
	id integer primary key autoincrement,
	name varchar(50) unique,
	password varchar(50)
);

create table Teacher(
	id integer primary key autoincrement,
	name varchar(50)
);

create table Room(
	id integer primary key autoincrement
);

create table Class(
	id integer primary key autoincrement,
	teacher_id integer foreign key,
	duration integer,
	name varchar(50)
);

create table Timetable(
	id integer primary key autoincrement
);

insert into User (name, password) values ("test", "password");
insert into User (name, password) values ("test2", "password");