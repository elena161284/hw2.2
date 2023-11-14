package pro.sky.demo.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.demo.Exception.StudentFindException;
import pro.sky.demo.model.Student;
import pro.sky.demo.repositories.StudentRepository;

import java.util.Collection;
import java.util.Collections;

@Service
public class StudentService {
    private final static Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student add(Student student) {
        logger.info("назван Add");
        return repository.save(student); // добавили
    }

    public Student get(long id) {
        logger.info("назван с аргументом {}",id);
        return repository.findById(id)
                .orElseThrow(()->new StudentFindException("Student with id")); // вывели
    }

    public Student update(Student student) {
        logger.info(" был назван update");
        return repository.findById(student.getId())
                .map(entity -> repository.save(student))
                .orElse(null);
    }

    public Student remove(long id) {
        logger.info("назван remove с аргументом {}",id);
        var entity = repository.findById(id).orElse(null);
        if (entity != null) {
            repository.delete(entity);
        }
        return entity; // удалили
    }
    public Collection<Student> filterByAge(int age) {
        logger.info("назван filterByAge с аргументом {}",age);
        return repository.findByAge(age);
    }

    public Collection<Student> filterAllByName(String name) {
        logger.info("назван filterAllByName с аргументом {}",name);
        return repository.findAllByName(name);
    }

    public Collection<String> filterAllStudent() {
        logger.info("назван filterAllStudent");
        return Collections.singleton(repository.toString());
    }

    public Collection<Student> filterByAgeBetween(int min, int max) {
        logger.info("назван filterByAgeBetween c аргументом {}:{}",min,max);
        return repository.findAllByAgeBetween(min, max);
    }
    public long studentsCount() {
        logger.error(" был назван studentsCount");
        return repository.getStudentCount();
    }

    public double averageAge() {
        logger.info(" был назван averageAge");
        return repository.getAverageAge();
    }

    public Collection<Student> lastFiveStudents() {
        logger.info(" был назван lastFiveStudents");
        return repository.getLastFiveStudents();
    }
}