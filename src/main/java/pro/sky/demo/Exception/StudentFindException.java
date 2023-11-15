package pro.sky.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StudentFindException extends RuntimeException{
    public StudentFindException(String studentWithId) {
    }
}
