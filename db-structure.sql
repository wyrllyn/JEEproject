create table Group(
	group_id int not null auto_increment,
	name varchar(50),
	primary key (group_id)
);

create table User(
	user_id int not null auto_increment,
	name unique varchar(50),
	password varchar(50),
	primary key (user_id)
);