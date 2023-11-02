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

    public Faculty createFaculty(Faculty faculty) {
        return repository.save(faculty); // добавили
    }

    public Faculty findFaculty(long id) {
        return repository.findById(id).orElse(null); // вывели
    }

    public Faculty editFaculty(Faculty faculty) {
        return repository.findById(faculty.getId())
                .map(entity -> repository.save(faculty))
                .orElse(null);
    }

    public Faculty deletedFaculty(long id) {
        var entity = repository.findById(id).orElse(null);
        if (entity != null) {
            repository.delete(entity);
        }
        return entity; // удалили
    }

    public Collection<Faculty> filterByNameOrColor(String name, String color) {
        return repository.findAllByNameOrColorIgnoreCase(name,color);
    }

}
