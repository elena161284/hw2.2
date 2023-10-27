package pro.sky.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.demo.model.Avatar;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    Optional<Avatar> findByStudentId(long id); // ищет по id студента
}
