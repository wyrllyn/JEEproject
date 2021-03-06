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

create table Teacher( -- just tells that a user is also a teacher
	id integer primary key autoincrement,
	user_id integer not null,
	foreign key (user_id) references User(id)
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
	foreign key (teacher_id) references Teacher(id),
	check (duration <= 120)
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
	foreign key (teacher_id) references Teacher(id)
);

create table Timetable(
	id integer primary key autoincrement,
	group_id integer not null,
	has_been_checked integer default 0,
	foreign key (group_id) references Group_(id)
);

create table TimetableMap(
	timetable_id integer not null,
	slot_id integer not null,
	room_id integer not null,
	foreign key (timetable_id) references Timetable(id),
	foreign key (slot_id) references Slot(id),
	foreign key (room_id) references Room(id)
);

-- All Created --
