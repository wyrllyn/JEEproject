-- User --
insert into User (name, password) values ("test", "password");
insert into User (name, password) values ("test2", "password");
-- Group --
insert into Group_ (name) values ("L3 Info");
insert into Group_ (name) values ("M1 Info");
insert into Group_ (name) values ("M2 Info SILI");
insert into Group_ (name) values ("M2 Info ID");
-- Teacher --
insert into Teacher (name) values ("Goeffon");
insert into Teacher (name) values ("Stephan");
insert into Teacher (name) values ("Hunault");
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
