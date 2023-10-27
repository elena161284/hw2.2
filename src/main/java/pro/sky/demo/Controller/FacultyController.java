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
    public Faculty getInfo(@PathVariable long id) {
        return facultyService.findFaculty(id);
    }
    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty){
        return facultyService.createFaculty(faculty);
    }
    @PutMapping()
    public Faculty editFaculty(@RequestBody Faculty faculty) {
        return facultyService.editFaculty(faculty);
    }
    @DeleteMapping("{id}")
    public Faculty deletedFaculty(@PathVariable Long id) {
        return facultyService.deletedFaculty(id);
    }
    @GetMapping("/byNameOrColorIgnoreCase")
    public Collection<Faculty> byNameOrColorIgnoreCase(
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String name)
    {
        return facultyService.filterByNameOrColor(color, name);
    }
}
