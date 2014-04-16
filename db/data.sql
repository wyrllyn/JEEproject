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
-- Slot -- for the record, now is: 1397651839947
insert into Slot (teacher_id, duration, class_type, beginning, name) values (2, 80, "CM", 0, "JEE");
insert into Slot (teacher_id, duration, class_type, beginning, name) values (4, 80, "TD", 0, "Framework");
insert into Slot (teacher_id, duration, class_type, beginning, name) values (2, 80, "CM", 120, "JEE");
insert into Slot (teacher_id, duration, class_type, beginning, name) values (4, 80, "TD", 120, "Framework");
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
-- The End --
