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
insert into Room (size) values (50);
insert into Room (size) values (50);
-- Slot -- for the record, Mon Jan 06 09:30:00 CET 2014 = 1388997000834
insert into Slot (teacher_id, duration, class_type, beginning, name) values (2, 80, "CM", 1388997000834, "JEE");
insert into Slot (teacher_id, duration, class_type, beginning, name) values (4, 80, "TD", 1388997000834, "Framework");
insert into Slot (teacher_id, duration, class_type, beginning, name) values (2, 80, "CM", 1389000600834, "JEE");
insert into Slot (teacher_id, duration, class_type, beginning, name) values (4, 80, "TD", 1389000600834, "Framework");

insert into Slot (teacher_id, duration, class_type, beginning, name) values (2, 80, "CM", 1388997000834, "Systèmes");
insert into Slot (teacher_id, duration, class_type, beginning, name) values (3, 80, "TD", 1388997000834, "IS");
insert into Slot (teacher_id, duration, class_type, beginning, name) values (2, 80, "CM", 1389000600834, "Systèmes");
insert into Slot (teacher_id, duration, class_type, beginning, name) values (3, 80, "TD", 1389000600834, "IS");
-- Timetable --
insert into Timetable (group_id) values (2); -- M1 Info
insert into Timetable (group_id) values (1); -- L3 Info
insert into Timetable (group_id) values (3); -- M2 Info SILI
insert into Timetable (group_id) values (4); -- M2 Info ID
-- TimetableMap --
---- M1 Info ----
insert into TimetableMap (timetable_id, slot_id, room_id) values (1, 1, 1);
insert into TimetableMap (timetable_id, slot_id, room_id) values (1, 2, 2);
insert into TimetableMap (timetable_id, slot_id, room_id) values (1, 3, 2);
insert into TimetableMap (timetable_id, slot_id, room_id) values (1, 4, 1);
---- L3 Info ----
insert into TimetableMap (timetable_id, slot_id, room_id) values (2, 5, 3);
insert into TimetableMap (timetable_id, slot_id, room_id) values (2, 6, 4);
insert into TimetableMap (timetable_id, slot_id, room_id) values (2, 7, 4);
insert into TimetableMap (timetable_id, slot_id, room_id) values (2, 8, 3);
-- The End --
