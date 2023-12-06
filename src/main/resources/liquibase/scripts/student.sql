-- liquibase formatted sql

-- changeset Elena:1

create index student_name on student (name);
create index faculty_name_color on faculty (name, color);

