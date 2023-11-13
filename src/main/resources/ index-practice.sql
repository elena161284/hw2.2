-- liquibase formatted sql

-- changeset Elena:1

create index student_name_index on student(name);
create index faculty_name_and_color_index on faculty(name, color);