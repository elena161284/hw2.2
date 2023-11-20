package pro.sky.demo.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.demo.Service.StudentService;
import pro.sky.demo.model.Student;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping("{id}")
    public Student get(@PathVariable long id) {
        return studentService.get(id);
    }

    @PostMapping
    public Student add(@RequestBody Student student) {
        return studentService.add(student); // добали студента в список
    }
    @PutMapping
    public Student update(@RequestBody Student student) {
        return studentService.update(student);
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable Long id) {
        studentService.remove(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/byAge") // localhost:8080/student/byAge?age=15
    public Collection<Student> byAge(@RequestParam int age) {
        return studentService.filterByAge(age);
    }
    @GetMapping("/byName")
    public  Collection<Student> byName(@RequestParam String name) {
        return studentService.filterAllByName(name);
    }
    @GetMapping("/byAll")
    public Collection<String> byAll (){
        return studentService.filterAllStudent();
    }
    @GetMapping("/byAgeBetween")
    public  Collection<Student> byAgeBetween(@RequestParam int min, @RequestParam int max) {
        return studentService.filterByAgeBetween(min, max);
    }
    @GetMapping("/count")
    public  long getCountOfStudents() {
        return studentService.studentsCount();
    }
    @GetMapping("/avgAge")
    public  double getAverageAge() {
        return studentService.averageAge();
    }
    @GetMapping("/lastfive")
    public  Collection<Student> getLastFiveStudents() {
        return studentService.lastFiveStudents();
    }
    @GetMapping("/nameStartsA")
    public Collection<String> getStudentsNameStartsA(){
        return studentService.getStudentsNameStartsA();
    }
    @GetMapping("/averageAge")
    public double getStreamAverageAge() {
        return studentService.streamAverageAge();
    }
}
