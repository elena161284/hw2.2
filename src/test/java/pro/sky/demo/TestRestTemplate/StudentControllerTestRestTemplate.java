package pro.sky.demo.TestRestTemplate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import pro.sky.demo.Controller.StudentController;
import pro.sky.demo.model.Faculty;
import pro.sky.demo.model.Student;
import pro.sky.demo.repositories.StudentRepository;

import java.sql.SQLOutput;
import java.util.Collection;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTestRestTemplate {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void testPostStudent() {
        var s = student("Гермиона",18);
        var result = restTemplate.postForObject("/student", s, Student.class);
        Assertions.assertThat(result.getName()).isEqualTo("Гермиона");
        Assertions.assertThat(result.getAge()).isEqualTo(18);
        Assertions.assertThat(result.getId()).isNotNull();
    }
    @Test
    public void testGetStudentById(){
        var s = student("Ron",19);
        var saved = restTemplate.postForObject("/student", s, Student.class);
        var result =restTemplate.getForObject( "/student/"+ saved.getId(), Student.class);
        Assertions.assertThat(result.getName()).isEqualTo("Ron");
        Assertions.assertThat(result.getAge()).isEqualTo(19);
    }

    @Test
    public void testDeleteStudent() throws Exception {
        var s = student("DelHarry", 119);
        var saved = restTemplate.postForObject("/student", s, Student.class);
        ResponseEntity<Student> studentEntity= restTemplate.exchange(
                "/student/"+ saved.getId(),
                HttpMethod.DELETE,
                null,
                Student.class
        );
        Assertions.assertThat(Objects.requireNonNull(studentEntity.getBody()).getName()).isEqualTo("DelHarry");
        Assertions.assertThat(Objects.requireNonNull(studentEntity.getBody()).getAge()).isEqualTo(119);
        var delHarry = restTemplate.getForObject("/student/" + saved.getId(), Student.class);
        Assertions.assertThat(delHarry).isNotNull();
    }

    @Test
    void testGetFacultyByStudent() {
        var savedFaculty = restTemplate.postForObject("/faculty", faculty("ppp","green"),Faculty.class);
        var s = student("Ron",19);
        s.setFaculties(savedFaculty);
        var savedStudent = restTemplate.postForObject("/student", s, Student.class); //получили сохраненного студента
        var result = restTemplate.getForObject("/student/" + savedStudent.getId() + "/faculty", Faculty.class);
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getName()).isEqualTo("ppp");
        Assertions.assertThat(result.getColor()).isEqualTo("green");


    }
    @Test
    public void testPutStudent() {
        var s = student("Harry",19);
        var saved = restTemplate.postForObject("/student", s, Student.class); // взяли студента
        saved.setName("Гермиона"); // заменили имя студента
        ResponseEntity<Student> studentEntity= restTemplate.exchange(
                "/student", HttpMethod.PUT, new HttpEntity<>(saved),Student.class
        );
        Assertions.assertThat(Objects.requireNonNull(studentEntity.getBody()).getName()).isEqualTo("Гермиона");
        Assertions.assertThat(Objects.requireNonNull(studentEntity.getBody()).getAge()).isEqualTo(18);
    }

    @Test
    public void testFilterByAge() {
        var s1=restTemplate.postForObject("/student",student("test1",16),Student.class);
        var s2=restTemplate.postForObject("/student",student("test2",17),Student.class);
        var s3=restTemplate.postForObject("/student",student("test3",18),Student.class);
        var s4=restTemplate.postForObject("/student",student("test4",19),Student.class);
        var s5=restTemplate.postForObject("/student",student("test5",18),Student.class);

        var result = restTemplate.exchange("/student/byAge?age=19",
                HttpMethod.GET, null, new ParameterizedTypeReference<Collection<Student>>(){});
        var students = result.getBody();
        Assertions.assertThat(students).isNotNull();
        Assertions.assertThat(students.size()).isEqualTo(2);
        Assertions.assertThat(students).containsExactly(s3,s5);
    }
    @Test
    public void testFilterByAgeBetween() {
        var s1=restTemplate.postForObject("/student",student("test1",16),Student.class);
        var s2=restTemplate.postForObject("/student",student("test2",17),Student.class);
        var s3=restTemplate.postForObject("/student",student("test3",18),Student.class);
        var s4=restTemplate.postForObject("/student",student("test4",19),Student.class);
        var s5=restTemplate.postForObject("/student",student("test5",18),Student.class);

        var result = restTemplate.exchange("/student/byAgeBetween?min=17&max=19",
                HttpMethod.GET, null, new ParameterizedTypeReference<Collection<Student>>(){});
        var students = result.getBody();
        Assertions.assertThat(students).isNotNull();
        Assertions.assertThat(students.size()).isEqualTo(2);
        Assertions.assertThat(students).containsExactly(s2,s3,s4,s5);
    }
    private static Faculty faculty(String name, String color) {
        var f = new Faculty();
        f.setName(name);
        f.setColor(color);
        return f;
    }
    private static Student student(String name, int age) {
        var s = new Student();
        s.setName(name);
        s.setAge(age);
        return s;
    }
}