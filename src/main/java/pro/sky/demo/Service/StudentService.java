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

    public Student add(Student student) {
        return repository.save(student); // добавили
    }

    public Student get(long id) {
        return repository.findById(id).orElse(null); // вывели
    }

    public Student update(Student student) {
        return repository.findById(student.getId())
                .map(entity -> repository.save(student))
                .orElse(null);
    }

    public Student remove(long id) {
        var entity = repository.findById(id).orElse(null);
        if (entity != null) {
            repository.delete(entity);
        }
        return entity; // удалили
    }
    public Collection<Student> filterByAge(int age) {
        return repository.findByAge(age);
    }

    public Collection<Student> filterAllByName(String name) {
        return repository.findAllByName(name);
    }

    public Collection<String> filterAllStudent() {
        return Collections.singleton(repository.toString());
    }

    public Collection<Student> filterByAgeBetween(int min, int max) {
        return repository.findAllByAgeBetween(min, max);
    }
    public long studentsCount() {
        return repository.getStudentCount();
    }

    public double averageAge() {
        return repository.getAverageAge();
    }

    public Collection<Student> lastFiveStudents() {
        return repository.getLastFiveStudents();
    }
}