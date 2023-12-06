package pro.sky.demo.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.demo.Exception.StudentFindException;
import pro.sky.demo.model.Student;
import pro.sky.demo.repositories.StudentRepository;

import java.sql.SQLOutput;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final static Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository repository;
    //private static final Object monitor=new Object();

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
        try {
            if (true) {
                throw new RuntimeException("DB Error!");
            }
            return repository.getStudentCount();
        } catch (RuntimeException e) {
            logger.error("Cannot get count of students", e);
        }
        return -1;
    }

    public double averageAge() {
        logger.info(" был назван averageAge");
        return repository.getAverageAge();
    }

    public Collection<Student> lastFiveStudents() {
        logger.info(" был назван lastFiveStudents");
        return repository.getLastFiveStudents();
    }
    public Collection<String> getStudentsNameStartsA() {
        return repository.findAll()
                .stream()
                .map(Student::getName)
                .map(String::toUpperCase)
                .filter(upperCase->upperCase.startsWith("A"))
                .sorted()
                .collect(Collectors.toList());
    }

    public double streamAverageAge() {
        return repository.findAll()
                .stream()
                .map(Student::getName)
                .mapToInt(Integer::parseInt)
                .average()
                .orElse(0d);
    }

    public void printNonSync() {
        var students = repository.findAll();
        System.out.println(students.get(0));
        System.out.println(students.get(1));

        //new Thread(new Runnable() {  //создаем параллельный поток ( new Thread())
        //            @Override
        //            public void run() {
        //                System.out.println(students.get(2));
        //                System.out.println(students.get(3));
        //            }
        //        }); можно свернуть->


        Thread t1=  new Thread(() -> {
            System.out.println(students.get(2));
            System.out.println(students.get(3));   //создали объект  потока ( new Thread())
        });

        Thread t2=  new Thread(() -> {
            System.out.println(students.get(4));
            System.out.println(students.get(5));   //создали объект  потока ( new Thread())
        });
        t1.start();
        t2.start();
        System.out.println("________________");
    }// запустили

    public void printSync() {
        var students = repository.findAll();
        printSynchronized(students.get(0));
        printSynchronized(students.get(1));
        Thread t1=  new Thread(() -> {
            printSynchronized(students.get(2));
            printSynchronized(students.get(3));
        });

        Thread t2=  new Thread(() -> {
            printSynchronized(students.get(4));
            printSynchronized(students.get(5));
        });
        t1.start();
        t2.start();
        System.out.println("________________");
    }

    private synchronized void printSynchronized(Object o) {
            System.out.println(o.toString());
        }
}
//private void printSynchronized(Object o) {
//    synchronized (monitor) {
//        System.out.println(o.toString());
//    }
// }