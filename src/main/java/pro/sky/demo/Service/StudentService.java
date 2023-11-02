package pro.sky.demo.Service;

import org.springframework.stereotype.Service;
import pro.sky.demo.model.Student;
import pro.sky.demo.repositories.StudentRepository;

import java.util.Collection;
import java.util.Collections;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student createStudent(Student student) {
        return repository.save(student); // добавили
    }

    public Student findStudent(long id) {
        return repository.findById(id).orElse(null); // вывели
    }

    public Student editStudent(Student student) {
        return repository.findById(student.getId())
                .map(entity -> repository.save(student))
                .orElse(null);
    }

    public Student deletedStudent(long id) {
        var entity = repository.findById(id).orElse(null);
        if (entity != null) {
            repository.delete(entity);
        }
        return entity; // удалили
    }
    public Collection<Student> filterByAge(int age) {
        return repository.findAllByAge(age);
    }

    public Collection<Student> filterByName(String name) {
        return repository.findAllByName(name);
    }

    public Collection<String> filterAllStudent() {
        return Collections.singleton(repository.toString());
    }

    public Collection<Student> filterByAgeBetween(int min, int max) {
        return repository.findAllByAgeBetween(min, max);
    }

}