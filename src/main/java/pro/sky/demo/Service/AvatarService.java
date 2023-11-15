package pro.sky.demo.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.demo.Exception.StudentFindException;
import pro.sky.demo.model.Avatar;
import pro.sky.demo.model.Student;
import pro.sky.demo.repositories.AvatarRepository;
import pro.sky.demo.repositories.StudentRepository;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.rmi.RemoteException;
import java.util.Collection;

@Service
public class AvatarService {
    private final String avatarsDir;
    private final StudentRepository studentRepository;
    private final AvatarRepository avatarRepository;

    public AvatarService(StudentRepository studentRepository,
                         AvatarRepository avatarRepository,
                         @Value("${avatars.dir}")String avatarsDir) {
        this.studentRepository = studentRepository;
        this.avatarRepository = avatarRepository;
        this.avatarsDir=avatarsDir;
    }

    public void upload(Long studentId, MultipartFile file) throws IOException {
        var student = studentRepository
                .findById(studentId)
                .orElseThrow(StudentFindException::new);

        var dir = Path.of(avatarsDir);
        if (!dir.toFile().exists()) {
            Files.createDirectories(dir);
        }
        var path = saveFile(file, student);// создали папку с файлом

        Avatar avatar = avatarRepository.findByStudentId(studentId).orElse(new Avatar());
        avatar.setFilePath(path);
        avatar.setData(file.getBytes());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setStudent(student);
        avatarRepository.save(avatar);  // сохранение на диск(в базу данных)
    }

    private String saveFile(MultipartFile file, Student student) throws RemoteException {
        var dotIndex = file.getOriginalFilename().lastIndexOf('.'); // индекс точки(.)
        var ext = file.getOriginalFilename().substring(dotIndex + 1);// получилми расширение файла(обрезали индекс строки до (.))
        var path = avatarsDir + "/" + student.getId() + "_" + student.getName()+"."+ext;// пишем путь к файлу
        try (var in = file.getInputStream();            //создаем поток, который читаем
             var out = new FileOutputStream(path)) {    //создаем поток, который пишем
            in.transferTo(out);
        } catch (IOException e) {
            throw new RemoteException();                //что вложить(файл) в путь
        }
        return path;
    }

    public Avatar find(long studentId) {
        return avatarRepository.findByStudentId(studentId).orElse(null);
    }

    public Collection<Avatar> find(int page, int pageSize) {
    return avatarRepository.findAll(PageRequest.of(page, pageSize)).getContent();
    }
}
