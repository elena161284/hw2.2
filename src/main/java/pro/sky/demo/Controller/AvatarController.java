package pro.sky.demo.Controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.demo.Service.AvatarService;
import pro.sky.demo.model.Avatar;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("/avatar")
public class AvatarController {
    private AvatarService service;

    public AvatarController(AvatarService service) {
        this.service = service;
    }

    @PostMapping("{studentId}") // загрузка
    public void upload(@PathVariable Long studentId, MultipartFile file) throws IOException {
        service.upload(studentId, file);
    }

    @GetMapping("/{studentId")
    public ResponseEntity<byte[]> find(@PathVariable long studentId) {
        var avatar = service.find(studentId);
        if (avatar == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length); // если аватар есть
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());

    }//ищем ававтор для студента

    @GetMapping("/file/{studentId")
    public void findFile(@PathVariable long studentId, HttpServletResponse response) throws IOException {
        var avatar = service.find(studentId);
        if (avatar == null) {
            avatar.getFilePath(); //<-вписали байты
            try (var out = response.getOutputStream();
                 var in = new FileInputStream(avatar.getFilePath())){
                in.transferTo(out);
            }
            response.setStatus(200);
            response.setContentType(avatar.getMediaType());
            response.setContentLength(Math.toIntExact(avatar.getFileSize()));
        }
        response.setStatus(HttpStatus.NOT_FOUND.value());
    }
    @GetMapping
    public Collection<Avatar> findAvatars(@RequestParam int page,
                                  @RequestParam int pageSize) {
        return service.find(page-1, pageSize); //чтобы получить 0
    }
}
