package pro.sky.demo.Controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.demo.Service.FacultyService;
import pro.sky.demo.model.Faculty;

import java.util.Collection;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }
    @GetMapping("{id}")
    public Faculty get(@PathVariable long id) {
        return facultyService.get(id);
    }
    @PostMapping
    public Faculty add(@RequestBody Faculty faculty){
        return facultyService.add(faculty);
    }
    @PutMapping()
    public Faculty update(@RequestBody Faculty faculty) {
        return facultyService.update(faculty);
    }
    @DeleteMapping("{id}")
    public Faculty remove(@PathVariable Long id) {
        return facultyService.remove(id);
    }

    @GetMapping("/byNameOrColor")
    public Collection<Faculty> byNameOrColor(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String color)
    {
        return facultyService.filterByNameOrColor(name, color);
    }
}
