package pro.sky.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.demo.model.Faculty;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Collection<Faculty> findAllByColor(String color);

    Collection<Faculty> findAllByNameOrColorIgnoreCase(String name, String color);


}

