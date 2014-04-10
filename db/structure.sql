create table Group_( --sqlite doesn't like something called Group
	id integer primary key autoincrement,
	name varchar(50) unique,
	timetable_id integer,
	foreign key (timetable_id) references timetable(id)
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
	id integer primary key autoincrement,
	size integer
);

create table Class(
	id integer primary key autoincrement,
	teacher_id integer,
	duration integer, -- in minutes
	name varchar(50),
	foreign key (teacher_id) references Teacher(id)
);

create table Slot(
	id integer primary key autoincrement,
	--class_id integer,
	--room_id integer,
	teacher_id integer,		-- maybe access through class table?
	duration integer,		-- in minutes
	class_type varchar(2),	-- CM, TD, TP
	beginning integer,		-- milliseconds since the dawn of time
	name varchar(50),		-- what's that for?
	foreign key (teacher_id)
);

create table Timetable(
	id integer primary key autoincrement
);

-- All Created --
