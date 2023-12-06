package pro.sky.demo.MockMvc;

import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import pro.sky.demo.Controller.StudentController;
import pro.sky.demo.Service.AvatarService;
import pro.sky.demo.Service.FacultyService;
import pro.sky.demo.Service.StudentService;
import pro.sky.demo.repositories.AvatarRepository;
import pro.sky.demo.repositories.FacultyRepository;
import pro.sky.demo.repositories.StudentRepository;

public class StudentControllerTestMockMvc {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    StudentRepository studentRepository;
    @MockBean
    AvatarRepository avatarRepository;
    @MockBean
    FacultyRepository facultyRepository;
    @SpyBean
    StudentService studentService;
    @SpyBean
    AvatarService avatarService;
    @SpyBean
    FacultyService facultyService;
    @InjectMocks
    StudentController studentController;

    


}
