-- Group --
insert into Group_ (name) values ("L3 Info");
insert into Group_ (name) values ("M1 Info");
insert into Group_ (name) values ("M2 Info SILI");
insert into Group_ (name) values ("M2 Info ID");
-- User --
insert into User (name, password) values ("testUsr", "password");
insert into User (name, password) values ("Goeffon", "password");
insert into User (name, password) values ("Stephan", "password");
insert into User (name, password) values ("Hunault", "password");
-- Teacher --
insert into Teacher (user_id) values (2);
insert into Teacher (user_id) values (3);
insert into Teacher (user_id) values (4);
-- Class -- (duration is in minutes)
insert into Class (duration, name) values (90, "A");
insert into Class (duration, name) values (80, "B");
insert into Class (duration, name) values (80, "C");
-- Room --
insert into Room (size) values (50);
insert into Room (size) values (50);
insert into Room (size) values (50);
-- Slot --
insert into Slot (teacher_id, duration, class_type, beginning, name) values (1, 80, "CM", 0, "Your Death");

-- The End --
