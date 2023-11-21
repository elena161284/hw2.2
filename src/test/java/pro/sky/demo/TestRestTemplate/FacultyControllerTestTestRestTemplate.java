package pro.sky.demo.TestRestTemplate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import pro.sky.demo.Controller.FacultyController;
import pro.sky.demo.model.Faculty;
import pro.sky.demo.model.Student;
import pro.sky.demo.repositories.FacultyRepository;
import pro.sky.demo.repositories.StudentRepository;

import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTestTestRestTemplate {

    @LocalServerPort
    private int port;

    @Autowired
    private FacultyController facultyController;
    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(facultyController).isNotNull();
    }
    @Test
    public void testPostFaculty () {
        var f = faculty( "ppp","black");
        var result = restTemplate.postForObject("/faculty", f, Faculty.class);
        Assertions.assertThat(result.getName()).isEqualTo("ppp");
        Assertions.assertThat(result.getColor()).isEqualTo("black");
    }
    @Test
    public void testGetFaculty(){
        var f = faculty("ttt","white");
        var saved = restTemplate.postForObject("/faculty", f, Faculty.class);
        var result =restTemplate.getForObject( "/faculty/"+ saved.getId(), Faculty.class);
        Assertions.assertThat(result.getId()).isEqualTo(2L);
        Assertions.assertThat(result.getName()).isEqualTo("ttt");
        Assertions.assertThat(result.getColor()).isEqualTo("white");
   }
    @Test
    public void testDeleteFaculty() throws Exception {
        var f = faculty( "ttt","white");
        var saved = restTemplate.postForObject("/faculty", f, Faculty.class);
        ResponseEntity<Faculty> facultyEntity= restTemplate.exchange(
                "/faculty", HttpMethod.DELETE, new HttpEntity<>(saved),Faculty.class
        );
        Assertions.assertThat(Objects.requireNonNull(facultyEntity.getBody()).getId()).isEqualTo(2L);
    }

    private static Faculty faculty(String name, String color) {
        var f = new Faculty();
        f.setName(name);
        f.setColor(color);
        return f;
    }
}