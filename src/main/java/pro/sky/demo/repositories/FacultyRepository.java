package pro.sky.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.demo.model.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
