create table vehicle(
                     id serial primari key,
                     brand varchar(225),
                     model varchar(225),
                     cost money);

create table  persons(
                     id serial primari key,
                     name varchar(225),
                     age int,
                     vehicle_owner boolean);


alter table person add column vehicle_id serial;
alter table vehicle add constraint unique_vehicle unique(model);

select student.name, student.age, faculty.name
from student
        inner join faculty on student.faculty_id = faculty_id;

select s.id, s.name, avtr.id
from student as s
         inner join avatar avtr on s.id = avtr.student_id;