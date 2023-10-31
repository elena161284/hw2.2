package pro.sky.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import pro.sky.demo.Controller.FacultyController;
import pro.sky.demo.Controller.StudentController;
import pro.sky.demo.model.Faculty;
import pro.sky.demo.model.Student;
import pro.sky.demo.repositories.FacultyRepository;
import pro.sky.demo.repositories.StudentRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Hw2ApplicationTest {
    @LocalServerPort
    private int port;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    private StudentController studentController;
    @Autowired
    private FacultyController facultyController;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void contextLoads() {
        Assertions.assertThat(studentController).isNotNull();
        Assertions.assertThat(facultyController).isNotNull();
    }

    @Test
    void findStudent() {
        Assertions.
                assertThat(this.testRestTemplate.getForObject(
                        "http://localhost:" + port + "/student/1", String.class)).isNotNull();
    }

    @Test
    void findFaculty() {
        Assertions.
                assertThat(this.testRestTemplate.getForObject(
                        "http://localhost:" + port + "/faculty/1", String.class)).isNotNull();
    }

    @Test
    void testPostStudent(){
        Student student = new Student(7L,"Son",29);
        Assertions.
                assertThat(this.testRestTemplate.postForObject(
                        "http://localhost:" + port + "/student", student, String.class)).isNotNull();
    }
    @Test
    void testPostFaculty(){
        Faculty faculty = new Faculty(7L,"Newcastle","black-white");
        Assertions.
                assertThat(this.testRestTemplate.postForObject(
                        "http://localhost:" + port + "/faculty", faculty, String.class)).isNotNull();
    }

    @Test
    void findByColor() {
        Assertions.
                assertThat(this.testRestTemplate.getForObject(
                        "http://localhost:" + port + "/faculty/?color=red", String.class)).isNotNull();

    }
    @Test
    void getFacultyOfStudent() {
        Assertions.
                assertThat(this.testRestTemplate.getForObject(
                        "http://localhost:" + port + "/faculty/?studentId=1", String.class)).isNotNull();

    }
    @Test
    void findByAge() {
        Assertions.
                assertThat(this.testRestTemplate.getForObject(
                        "http://localhost:" + port + "/students/?age=29", String.class)).isNotNull();

    }
    @Test
    void findByAgeBetween() {
        Assertions.
                assertThat(this.testRestTemplate.getForObject(
                        "http://localhost:" + port + "/students/?minAge=25&&maxAge=30", String.class)).isNotNull();

    }

    @Test
    void findStudentsByFaculty() {
        Assertions.
                assertThat(this.testRestTemplate.getForObject(
                        "http://localhost:" + port + "/students/?id=1", String.class)).isNotNull();

    }
}