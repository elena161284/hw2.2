package pro.sky.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.demo.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
