package pro.sky.demo.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTestRestTemplate {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void testGetStudentById() throws Exception {
        Assertions
                .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student" + "/by-id", String.class))
                .isNotNull();
    }

    @Test
    public void testPostStudent() throws Exception {
        Student student = new Student();
        student.setId(5L);
        student.setName("Гермиона Грейнджер");
        student.setAge(18);

        Assertions
                .assertThat(this.testRestTemplate.postForObject("http://localhost:" + port + "/student", student, String.class))
                .isNotNull();
    }

    @Test
    public void testGetStudentByAgeBetween() throws Exception {
        Assertions
                .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student" + "/By-Age-Between?min=" + 20 + "&max=" + 30, String.class))
                .isNotNull();
    }

    @Test
    public void testGetFacultyByStudentId() throws Exception {
        Assertions
                .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student" + "/faculty-by-student-id", String.class))
                .isNotNull();
    }

    @Test
    public void testDeleteStudent() throws Exception {
        ResponseEntity<Void> resp = testRestTemplate.exchange("http://localhost:" + port + "/student", HttpMethod.DELETE, null, Void.class);
    }

    @Test
    public void testPutStudent() throws Exception {
        Student student = new Student();
        student.setId(5L);
        student.setName("Гермиона Грейнджер");
        student.setAge(20);

        Assertions
                .assertThat(this.testRestTemplate.postForObject("http://localhost:" + port + "/student", student, String.class))
                .isNotNull();
    }
}