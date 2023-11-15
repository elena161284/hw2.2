package pro.sky.demo.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.demo.model.Faculty;
import pro.sky.demo.repositories.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyService {
    private final static Logger logger = LoggerFactory.getLogger(FacultyService.class);

    private final FacultyRepository repository;

    public FacultyService(FacultyRepository repository) {
        this.repository = repository;
    }

    public Faculty add(Faculty faculty) {
        logger.info("назван Add");
        return repository.save(faculty); // добавили
    }

    public Faculty get(long id) {
        logger.info("назван с аргументом {}",id);
        return repository.findById(id).orElse(null); // вывели
    }

    public Faculty update(Faculty faculty) {
        logger.info("назван update");
        return repository.findById(faculty.getId()) //если нашли
                .map(entity -> repository.save(faculty))// то обновляем
                .orElse(null); // или нал(возвращается обноаленная сущность)
    }

    public Faculty remove(long id) {
        logger.info("назван remove c аргументом {}",id);
        var entity = repository.findById(id).orElse(null);
        if (entity != null) {
            repository.delete(entity);
        }
        return entity; // удалили
    }

    public Collection<Faculty> filterByNameOrColor(String color, String name) {
        logger.info("назван filterByNameOrColor с аргументом {}:{}",color,name);
        return repository.findAllByNameOrColorIgnoreCase(color, name);
    }
}
