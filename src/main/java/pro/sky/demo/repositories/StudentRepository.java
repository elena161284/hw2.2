package pro.sky.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pro.sky.demo.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByAge(int age);

    Collection<Student> findAllByName(String name);

    Collection<Student> findAllByAgeBetween(int min, int max);

    @Query(value = "select count(*) from student", nativeQuery = true)
    long getStudentCount();

    @Query(value = "select avg(age) from student", nativeQuery = true)
     double getAverageAge();

    @Query(value = "select * from student order by id desc limit 5", nativeQuery = true)
    Collection<Student> getLastFiveStudents();
}
