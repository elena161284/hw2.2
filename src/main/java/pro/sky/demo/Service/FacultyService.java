package pro.sky.demo.Service;

import org.springframework.stereotype.Service;
import pro.sky.demo.model.Faculty;
import pro.sky.demo.repositories.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyService {
    private final FacultyRepository repository;

    public FacultyService(FacultyRepository repository) {
        this.repository = repository;
    }

    public Faculty add(Faculty faculty) {
        return repository.save(faculty); // добавили
    }

    public Faculty get(long id) {
        return repository.findById(id).orElse(null); // вывели
    }

    public Faculty update(Faculty faculty) {
        return repository.findById(faculty.getId()) //если нашли
                .map(entity -> repository.save(faculty))// то обновляем
                .orElse(null); // или нал(возвращается обноаленная сущность)
    }

    public Faculty remove(long id) {
        var entity = repository.findById(id).orElse(null);
        if (entity != null) {
            repository.delete(entity);
        }
        return entity; // удалили
    }

    public Collection<Faculty> filterByNameOrColor(String color, String name) {
        return repository.findAllByNameOrColorIgnoreCase(color, name);
    }
}
